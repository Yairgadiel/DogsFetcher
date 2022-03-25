package com.gy.demo.dog.model.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gy.demo.dogs.model.Dog
import com.gy.demo.dogs.model.local.AppDatabase
import com.gy.demo.dogs.model.local.DogsDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
@RunWith(AndroidJUnit4::class)
class DogsDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // A JUnit Test Rule that swaps the background executor
    // used by the Architecture Components with a different one
    // which executes each task synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dogsDao: DogsDao

    @Before
    fun setup() {
        hiltRule.inject()
        dogsDao = database.dogsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsert_Regular() = runBlockingTest {
        val dog = Dog(1, "path", "Snoopy", 150)
        dogsDao.insertDog(dog)
        val dogs = dogsDao.getAllDogs().first()
        Assert.assertTrue(dogs.contains(dog))
    }

    @Test
    fun testInsert_AutoIncrement() = runBlockingTest {
        val dog = Dog(0, "path", "Snoopy", 150)
        dogsDao.insertDog(dog)
        val dogs = dogsDao.getAllDogs().first()

        Assert.assertTrue(dogs[0].uid == 1)
    }

    @Test
    fun testDelete() = runBlockingTest {
        val dog = Dog(1, "path", "Snoopy", 150)
        dogsDao.insertDog(dog)
        dogsDao.deleteDog(dog)
        val dogs = dogsDao.getAllDogs().first()
        Assert.assertTrue(dogs.isEmpty())
    }

    @Test
    fun testGetById() = runBlockingTest {
        val dog = Dog(1, "path", "Snoopy", 150)
        dogsDao.insertDog(dog)
        val dbDog = dogsDao.getDogById(1).first()
        Assert.assertEquals(dog, dbDog)
    }

}