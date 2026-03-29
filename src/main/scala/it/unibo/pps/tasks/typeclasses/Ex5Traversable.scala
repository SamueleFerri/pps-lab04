package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.Sequences.Sequence
import Sequence.*
import it.unibo.pps.u03.Optionals.Optional
import it.unibo.pps.u03.Optionals.Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  private def log[A](a: A): Unit = println("The next element is: " + a)

//  def logAll[A](seq: Sequence[A]): Unit = seq match
//    case Cons(h, t) => log(h); logAll(t)
//    case _ => ()

  trait Traversable[T[_]]:
    def traverse[A](container: T[A])(consumer: A => Unit): Unit

  private def logAll[T[_]: Traversable, A](container: T[A])(consumer: A => Unit): Unit =
    val traversable = summon[Traversable[T]]
    traversable.traverse(container)(consumer)

  given Traversable[Sequence] with
    def traverse[A](container: Sequence[A])(consumer: A => Unit): Unit = container match
      case Cons(h, t) => consumer(h); traverse(t)(consumer)
      case _ => ()

  given Traversable[Optional] with
    def traverse[A](container: Optional[A])(consumer: A => Unit): Unit = container match
      case Just(value) => consumer(value)
      case _ => ()

  @main def tryTraversable(): Unit =
    val seq = Cons(10, Cons(20, Cons(30, Nil())))
    val opt = Just(42)
    val emptyOpt: Optional[Int] = Empty()
  
    println("Sequence con log")
    logAll(seq)(log)
  
    println("\nSequence con println")
    logAll(seq)(println(_))
  
    println("\nOptional con log")
    logAll(opt)(log)
  
    println("\nOptional con println")
    logAll(opt)(println(_))
  
    println("\nEmpty Optional con log")
    logAll(emptyOpt)(log)