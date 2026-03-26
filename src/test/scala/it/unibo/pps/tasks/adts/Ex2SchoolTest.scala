package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import SchoolModel.BasicSchoolModule.*
import it.unibo.pps.u03.extensionmethods.Sequences
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence.*

class SchooTest:

  val t1: String = teacher("Viroli")
  val t2: String = teacher("Ricci")
  val c1: String = course("PPS")
  val c2: String = course("OOP")
  val manualSchool: School = Cons((t1, c1), Cons((t1, c2), Cons((t2, c1), Nil())))
  val extractedCourses: Sequences.Sequence[String] = manualSchool.courses
  val extractedTeachers: Sequences.Sequence[String] = manualSchool.teachers
  val school: Sequences.Sequence[(String, String)] = emptySchool
  val school1: Sequences.Sequence[(String, String)] = school.setTeacherToCourse(t1, c1)
  val school2: Sequences.Sequence[(String, String)] = school1.setTeacherToCourse(t1, c2)

  @Test
  def testCourses(): Unit =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Cons(c1, Cons(c2, Nil())), extractedCourses)


  @Test
  def testTeachers(): Unit =
    assertEquals(Nil(), emptySchool.teachers)
    assertEquals(Cons(t1, Cons(t2, Nil())), extractedTeachers)

  @Test
  def testSetTeacherToCourse(): Unit =
    assertEquals(Cons((t1, c1), Nil()), school1)
    assertEquals(Cons((t1, c2), Cons((t1, c1), Nil())), school2)