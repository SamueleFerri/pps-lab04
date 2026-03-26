package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import SchoolModel.BasicSchoolModule.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence.*

class SchooTest:

  @Test
  def testCourses(): Unit =
    assertEquals(Nil(), emptySchool.courses)

    val t1 = teacher("Viroli")
    val t2 = teacher("Ricci")
    val c1 = course("PPS")
    val c2 = course("OOP")

    val manualSchool: School = Cons((t1, c1), Cons((t1, c2), Cons((t2, c1), Nil())))
    val extractedCourses = manualSchool.courses
    assertEquals(Cons(c1, Cons(c2, Nil())), extractedCourses)


  @Test
  def testTeachers(): Unit =
    assertEquals(Nil(), emptySchool.teachers)

    val t1 = teacher("Viroli")
    val t2 = teacher("Ricci")
    val c1 = course("PPS")
    val c2 = course("OOP")

    val manualSchool: School = Cons((t1, c1), Cons((t2, c1), Cons((t1, c2), Nil())))
    val extractedTeachers = manualSchool.teachers
    assertEquals(Cons(t1, Cons(t2, Nil())), extractedTeachers)