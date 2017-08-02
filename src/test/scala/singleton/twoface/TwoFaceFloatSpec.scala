//package singleton.twoface
//
//import singleton.twoface.math._
//import org.scalacheck.Properties
//import shapeless.test.illTyped
//import singleton.TestUtils._
//import singleton.ops._
//
//class TwoFaceFloatSpec extends Properties("TwoFace.Float") {
//  property("Implicit Creation[]") = {
//    val a = implicitly[TwoFace.Float[2.0f]]
//    a.getValue == 2.0f && a.isLiteral
//  }
//  property("Safe Creation[]") = {
//    val a = TwoFace.Float[2.0f]
//    a.getValue == 2.0f && a.isLiteral
//  }
//  property("Safe Creation()") = {
//    val a = TwoFace.Float(2.0f)
//    a.getValue == 2.0f && a.isLiteral
//  }
//  property("Unsafe Creation()") = {
//    val a = TwoFace.Float(us(2.0f))
//    a.getValue == 2.0f && !a.isLiteral
//  }
//
//  property("Safe ifThenElse") = verifyTF(ifThenElse(true, 1.0f, 2.0f), 1.0f)
//  property("Unsafe ifThenElse") = verifyTF(ifThenElse(us(false), 1.0f, 2.0f), us(2.0f))
//
//  property("Safe Float + Safe Char") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Char('\u0001'), 3.0f)
//  property("Safe Float + Unsafe Char") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Char(us('\u0001')), us(3.0f))
//  property("Unsafe Float + Safe Char") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Char('\u0001'), us(3.0f))
//  property("Unsafe Float + Unsafe Char") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Char(us('\u0001')), us(3.0f))
//  property("Safe Float + Safe Int") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Int(1), 3.0f)
//  property("Safe Float + Unsafe Int") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Int(us(1)), us(3.0f))
//  property("Unsafe Float + Safe Int") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Int(1), us(3.0f))
//  property("Unsafe Float + Unsafe Int") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Int(us(1)), us(3.0f))
//  property("Safe Float + Safe Long") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Long(1L), 3.0f)
//  property("Safe Float + Unsafe Long") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Long(us(1L)), us(3.0f))
//  property("Unsafe Float + Safe Long") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Long(1L), us(3.0f))
//  property("Unsafe Float + Unsafe Long") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Long(us(1L)), us(3.0f))
//  property("Safe Float + Safe Float") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Float(1.0f), 3.0f)
//  property("Safe Float + Unsafe Float") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Float(us(1.0f)), us(3.0f))
//  property("Unsafe Float + Safe Float") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Float(1.0f), us(3.0f))
//  property("Unsafe Float + Unsafe Float") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Float(us(1.0f)), us(3.0f))
//  property("Safe Float + Safe Double") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Double(1.0), 3.0)
//  property("Safe Float + Unsafe Double") = verifyTF(TwoFace.Float(2.0f) + TwoFace.Double(us(1.0)), us(3.0))
//  property("Unsafe Float + Safe Double") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Double(1.0), us(3.0))
//  property("Unsafe Float + Unsafe Double") = verifyTF(TwoFace.Float(us(2.0f)) + TwoFace.Double(us(1.0)), us(3.0))
//
//  property("Safe Float - Safe Char") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Char('\u0001'), 1.0f)
//  property("Safe Float - Unsafe Char") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Char(us('\u0001')), us(1.0f))
//  property("Unsafe Float - Safe Char") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Char('\u0001'), us(1.0f))
//  property("Unsafe Float - Unsafe Char") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Char(us('\u0001')), us(1.0f))
//  property("Safe Float - Safe Int") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Int(1), 1.0f)
//  property("Safe Float - Unsafe Int") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Int(us(1)), us(1.0f))
//  property("Unsafe Float - Safe Int") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Int(1), us(1.0f))
//  property("Unsafe Float - Unsafe Int") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Int(us(1)), us(1.0f))
//  property("Safe Float - Safe Long") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Long(1L), 1.0f)
//  property("Safe Float - Unsafe Long") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Long(us(1L)), us(1.0f))
//  property("Unsafe Float - Safe Long") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Long(1L), us(1.0f))
//  property("Unsafe Float - Unsafe Long") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Long(us(1L)), us(1.0f))
//  property("Safe Float - Safe Float") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Float(1.0f), 1.0f)
//  property("Safe Float - Unsafe Float") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Float(us(1.0f)), us(1.0f))
//  property("Unsafe Float - Safe Float") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Float(1.0f), us(1.0f))
//  property("Unsafe Float - Unsafe Float") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Float(us(1.0f)), us(1.0f))
//  property("Safe Float - Safe Double") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Double(1.0), 1.0)
//  property("Safe Float - Unsafe Double") = verifyTF(TwoFace.Float(2.0f) - TwoFace.Double(us(1.0)), us(1.0))
//  property("Unsafe Float - Safe Double") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Double(1.0), us(1.0))
//  property("Unsafe Float - Unsafe Double") = verifyTF(TwoFace.Float(us(2.0f)) - TwoFace.Double(us(1.0)), us(1.0))
//
//  property("Safe Float * Safe Char") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Char('\u0001'), 2.0f)
//  property("Safe Float * Unsafe Char") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Char(us('\u0001')), us(2.0f))
//  property("Unsafe Float * Safe Char") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Char('\u0001'), us(2.0f))
//  property("Unsafe Float * Unsafe Char") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Char(us('\u0001')), us(2.0f))
//  property("Safe Float * Safe Int") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Int(1), 2.0f)
//  property("Safe Float * Unsafe Int") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Int(us(1)), us(2.0f))
//  property("Unsafe Float * Safe Int") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Int(1), us(2.0f))
//  property("Unsafe Float * Unsafe Int") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Int(us(1)), us(2.0f))
//  property("Safe Float * Safe Long") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Long(1L), 2.0f)
//  property("Safe Float * Unsafe Long") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Long(us(1L)), us(2.0f))
//  property("Unsafe Float * Safe Long") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Long(1L), us(2.0f))
//  property("Unsafe Float * Unsafe Long") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Long(us(1L)), us(2.0f))
//  property("Safe Float * Safe Float") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Float(1.0f), 2.0f)
//  property("Safe Float * Unsafe Float") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Float(us(1.0f)), us(2.0f))
//  property("Unsafe Float * Safe Float") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Float(1.0f), us(2.0f))
//  property("Unsafe Float * Unsafe Float") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Float(us(1.0f)), us(2.0f))
//  property("Safe Float * Safe Double") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Double(1.0), 2.0)
//  property("Safe Float * Unsafe Double") = verifyTF(TwoFace.Float(2.0f) * TwoFace.Double(us(1.0)), us(2.0))
//  property("Unsafe Float * Safe Double") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Double(1.0), us(2.0))
//  property("Unsafe Float * Unsafe Double") = verifyTF(TwoFace.Float(us(2.0f)) * TwoFace.Double(us(1.0)), us(2.0))
//
//  property("Safe Float / Safe Char") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Char('\u0002'), 3.0f)
//  property("Safe Float / Unsafe Char") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Char(us('\u0002')), us(3.0f))
//  property("Unsafe Float / Safe Char") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Char('\u0002'), us(3.0f))
//  property("Unsafe Float / Unsafe Char") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Char(us('\u0002')), us(3.0f))
//  property("Safe Float / Safe Int") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Int(2), 3.0f)
//  property("Safe Float / Unsafe Int") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Int(us(2)), us(3.0f))
//  property("Unsafe Float / Safe Int") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Int(2), us(3.0f))
//  property("Unsafe Float / Unsafe Int") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Int(us(2)), us(3.0f))
//  property("Safe Float / Safe Long") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Long(2L), 3.0f)
//  property("Safe Float / Unsafe Long") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Long(us(2L)), us(3.0f))
//  property("Unsafe Float / Safe Long") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Long(2L), us(3.0f))
//  property("Unsafe Float / Unsafe Long") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Long(us(2L)), us(3.0f))
//  property("Safe Float / Safe Float") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Float(2.0f), 3.0f)
//  property("Safe Float / Unsafe Float") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Float(us(2.0f)), us(3.0f))
//  property("Unsafe Float / Safe Float") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Float(2.0f), us(3.0f))
//  property("Unsafe Float / Unsafe Float") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Float(us(2.0f)), us(3.0f))
//  property("Safe Float / Safe Double") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Double(2.0), 3.0)
//  property("Safe Float / Unsafe Double") = verifyTF(TwoFace.Float(6.0f) / TwoFace.Double(us(2.0)), us(3.0))
//  property("Unsafe Float / Safe Double") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Double(2.0), us(3.0))
//  property("Unsafe Float / Unsafe Double") = verifyTF(TwoFace.Float(us(6.0f)) / TwoFace.Double(us(2.0)), us(3.0))
//
//  property("Safe Float % Safe Char") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Char('\u0004'), 3.0f)
//  property("Safe Float % Unsafe Char") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Char(us('\u0004')), us(3.0f))
//  property("Unsafe Float % Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Char('\u0004'), us(3.0f))
//  property("Unsafe Float % Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Char(us('\u0004')), us(3.0f))
//  property("Safe Float % Safe Int") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Int(4), 3.0f)
//  property("Safe Float % Unsafe Int") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Int(us(4)), us(3.0f))
//  property("Unsafe Float % Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Int(4), us(3.0f))
//  property("Unsafe Float % Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Int(us(4)), us(3.0f))
//  property("Safe Float % Safe Long") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Long(4L), 3.0f)
//  property("Safe Float % Unsafe Long") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Long(us(4L)), us(3.0f))
//  property("Unsafe Float % Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Long(4L), us(3.0f))
//  property("Unsafe Float % Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Long(us(4L)), us(3.0f))
//  property("Safe Float % Safe Float") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Float(4.0f), 3.0f)
//  property("Safe Float % Unsafe Float") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Float(us(4.0f)), us(3.0f))
//  property("Unsafe Float % Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Float(4.0f), us(3.0f))
//  property("Unsafe Float % Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Float(us(4.0f)), us(3.0f))
//  property("Safe Float % Safe Double") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Double(4.0), 3.0)
//  property("Safe Float % Unsafe Double") = verifyTF(TwoFace.Float(7.0f) % TwoFace.Double(us(4.0)), us(3.0))
//  property("Unsafe Float % Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Double(4.0), us(3.0))
//  property("Unsafe Float % Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) % TwoFace.Double(us(4.0)), us(3.0))
//
//  property("Safe Float < Safe Char") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Char('\u0004'), false)
//  property("Safe Float < Unsafe Char") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Char(us('\u0004')), us(false))
//  property("Unsafe Float < Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Char('\u0004'), us(false))
//  property("Unsafe Float < Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Char(us('\u0004')), us(false))
//  property("Safe Float < Safe Int") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Int(4), false)
//  property("Safe Float < Unsafe Int") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Int(us(4)), us(false))
//  property("Unsafe Float < Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Int(4), us(false))
//  property("Unsafe Float < Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Int(us(4)), us(false))
//  property("Safe Float < Safe Long") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Long(4L), false)
//  property("Safe Float < Unsafe Long") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Long(us(4L)), us(false))
//  property("Unsafe Float < Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Long(4L), us(false))
//  property("Unsafe Float < Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Long(us(4L)), us(false))
//  property("Safe Float < Safe Float") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Float(4.0f), false)
//  property("Safe Float < Unsafe Float") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Float(us(4.0f)), us(false))
//  property("Unsafe Float < Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Float(4.0f), us(false))
//  property("Unsafe Float < Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Float(us(4.0f)), us(false))
//  property("Safe Float < Safe Double") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Double(4.0), false)
//  property("Safe Float < Unsafe Double") = verifyTF(TwoFace.Float(7.0f) < TwoFace.Double(us(4.0)), us(false))
//  property("Unsafe Float < Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Double(4.0), us(false))
//  property("Unsafe Float < Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) < TwoFace.Double(us(4.0)), us(false))
//
//  property("Safe Float > Safe Char") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Char('\u0004'), true)
//  property("Safe Float > Unsafe Char") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Char(us('\u0004')), us(true))
//  property("Unsafe Float > Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Char('\u0004'), us(true))
//  property("Unsafe Float > Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Char(us('\u0004')), us(true))
//  property("Safe Float > Safe Int") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Int(4), true)
//  property("Safe Float > Unsafe Int") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Int(us(4)), us(true))
//  property("Unsafe Float > Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Int(4), us(true))
//  property("Unsafe Float > Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Int(us(4)), us(true))
//  property("Safe Float > Safe Long") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Long(4L), true)
//  property("Safe Float > Unsafe Long") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Long(us(4L)), us(true))
//  property("Unsafe Float > Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Long(4L), us(true))
//  property("Unsafe Float > Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Long(us(4L)), us(true))
//  property("Safe Float > Safe Float") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Float(4.0f), true)
//  property("Safe Float > Unsafe Float") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Float(us(4.0f)), us(true))
//  property("Unsafe Float > Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Float(4.0f), us(true))
//  property("Unsafe Float > Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Float(us(4.0f)), us(true))
//  property("Safe Float > Safe Double") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Double(4.0), true)
//  property("Safe Float > Unsafe Double") = verifyTF(TwoFace.Float(7.0f) > TwoFace.Double(us(4.0)), us(true))
//  property("Unsafe Float > Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Double(4.0), us(true))
//  property("Unsafe Float > Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) > TwoFace.Double(us(4.0)), us(true))
//
//  property("Safe Float <= Safe Char") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Char('\u0004'), false)
//  property("Safe Float <= Unsafe Char") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Char(us('\u0004')), us(false))
//  property("Unsafe Float <= Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Char('\u0004'), us(false))
//  property("Unsafe Float <= Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Char(us('\u0004')), us(false))
//  property("Safe Float <= Safe Int") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Int(4), false)
//  property("Safe Float <= Unsafe Int") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Int(us(4)), us(false))
//  property("Unsafe Float <= Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Int(4), us(false))
//  property("Unsafe Float <= Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Int(us(4)), us(false))
//  property("Safe Float <= Safe Long") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Long(4L), false)
//  property("Safe Float <= Unsafe Long") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Long(us(4L)), us(false))
//  property("Unsafe Float <= Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Long(4L), us(false))
//  property("Unsafe Float <= Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Long(us(4L)), us(false))
//  property("Safe Float <= Safe Float") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Float(4.0f), false)
//  property("Safe Float <= Unsafe Float") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Float(us(4.0f)), us(false))
//  property("Unsafe Float <= Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Float(4.0f), us(false))
//  property("Unsafe Float <= Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Float(us(4.0f)), us(false))
//  property("Safe Float <= Safe Double") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Double(4.0), false)
//  property("Safe Float <= Unsafe Double") = verifyTF(TwoFace.Float(7.0f) <= TwoFace.Double(us(4.0)), us(false))
//  property("Unsafe Float <= Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Double(4.0), us(false))
//  property("Unsafe Float <= Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) <= TwoFace.Double(us(4.0)), us(false))
//
//  property("Safe Float >= Safe Char") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Char('\u0004'), true)
//  property("Safe Float >= Unsafe Char") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Char(us('\u0004')), us(true))
//  property("Unsafe Float >= Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Char('\u0004'), us(true))
//  property("Unsafe Float >= Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Char(us('\u0004')), us(true))
//  property("Safe Float >= Safe Int") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Int(4), true)
//  property("Safe Float >= Unsafe Int") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Int(us(4)), us(true))
//  property("Unsafe Float >= Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Int(4), us(true))
//  property("Unsafe Float >= Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Int(us(4)), us(true))
//  property("Safe Float >= Safe Long") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Long(4L), true)
//  property("Safe Float >= Unsafe Long") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Long(us(4L)), us(true))
//  property("Unsafe Float >= Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Long(4L), us(true))
//  property("Unsafe Float >= Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Long(us(4L)), us(true))
//  property("Safe Float >= Safe Float") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Float(4.0f), true)
//  property("Safe Float >= Unsafe Float") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Float(us(4.0f)), us(true))
//  property("Unsafe Float >= Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Float(4.0f), us(true))
//  property("Unsafe Float >= Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Float(us(4.0f)), us(true))
//  property("Safe Float >= Safe Double") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Double(4.0), true)
//  property("Safe Float >= Unsafe Double") = verifyTF(TwoFace.Float(7.0f) >= TwoFace.Double(us(4.0)), us(true))
//  property("Unsafe Float >= Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Double(4.0), us(true))
//  property("Unsafe Float >= Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) >= TwoFace.Double(us(4.0)), us(true))
//
//  property("Safe Float == Regular Safe Char") = verifyTF(TwoFace.Float(7.0f) == ('\u0007'), true)
//  property("Safe Float == Regular Unsafe Char") = verifyTF(TwoFace.Float(7.0f) == (us('\u0007')), us(true))
//  property("Unsafe Float == Regular Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) == ('\u0007'), us(true))
//  property("Unsafe Float == Regular Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) == (us('\u0007')), us(true))
//  property("Safe Float == Regular Safe Int") = verifyTF(TwoFace.Float(7.0f) == (7), true)
//  property("Safe Float == Regular Unsafe Int") = verifyTF(TwoFace.Float(7.0f) == (us(7)), us(true))
//  property("Unsafe Float == Regular Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) == (7), us(true))
//  property("Unsafe Float == Regular Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) == (us(7)), us(true))
//  property("Safe Float == Regular Safe Long") = verifyTF(TwoFace.Float(7.0f) == (7L), true)
//  property("Safe Float == Regular Unsafe Long") = verifyTF(TwoFace.Float(7.0f) == (us(7L)), us(true))
//  property("Unsafe Float == Regular Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) == (7L), us(true))
//  property("Unsafe Float == Regular Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) == (us(7L)), us(true))
//  property("Safe Float == Regular Safe Float") = verifyTF(TwoFace.Float(7.0f) == (7.0f), true)
//  property("Safe Float == Regular Unsafe Float") = verifyTF(TwoFace.Float(7.0f) == (us(7.0f)), us(true))
//  property("Unsafe Float == Regular Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) == (7.0f), us(true))
//  property("Unsafe Float == Regular Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) == (us(7.0f)), us(true))
//  property("Safe Float == Regular Safe Double") = verifyTF(TwoFace.Float(7.0f) == (7.0), true)
//  property("Safe Float == Regular Unsafe Double") = verifyTF(TwoFace.Float(7.0f) == (us(7.0)), us(true))
//  property("Unsafe Float == Regular Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) == (7.0), us(true))
//  property("Unsafe Float == Regular Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) == (us(7.0)), us(true))
//
//  property("Safe Float == Safe Char") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Char('\u0007'), true)
//  property("Safe Float == Unsafe Char") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Char(us('\u0007')), us(true))
//  property("Unsafe Float == Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Char('\u0007'), us(true))
//  property("Unsafe Float == Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Char(us('\u0007')), us(true))
//  property("Safe Float == Safe Int") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Int(7), true)
//  property("Safe Float == Unsafe Int") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Int(us(7)), us(true))
//  property("Unsafe Float == Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Int(7), us(true))
//  property("Unsafe Float == Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Int(us(7)), us(true))
//  property("Safe Float == Safe Long") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Long(7L), true)
//  property("Safe Float == Unsafe Long") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Long(us(7L)), us(true))
//  property("Unsafe Float == Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Long(7L), us(true))
//  property("Unsafe Float == Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Long(us(7L)), us(true))
//  property("Safe Float == Safe Float") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Float(7.0f), true)
//  property("Safe Float == Unsafe Float") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Float(us(7.0f)), us(true))
//  property("Unsafe Float == Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Float(7.0f), us(true))
//  property("Unsafe Float == Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Float(us(7.0f)), us(true))
//  property("Safe Float == Safe Double") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Double(7.0), true)
//  property("Safe Float == Unsafe Double") = verifyTF(TwoFace.Float(7.0f) == TwoFace.Double(us(7.0)), us(true))
//  property("Unsafe Float == Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Double(7.0), us(true))
//  property("Unsafe Float == Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) == TwoFace.Double(us(7.0)), us(true))
//
//  property("Safe Float != Safe Char") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Char('\u0007'), false)
//  property("Safe Float != Unsafe Char") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Char(us('\u0007')), us(false))
//  property("Unsafe Float != Safe Char") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Char('\u0007'), us(false))
//  property("Unsafe Float != Unsafe Char") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Char(us('\u0007')), us(false))
//  property("Safe Float != Safe Int") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Int(7), false)
//  property("Safe Float != Unsafe Int") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Int(us(7)), us(false))
//  property("Unsafe Float != Safe Int") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Int(7), us(false))
//  property("Unsafe Float != Unsafe Int") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Int(us(7)), us(false))
//  property("Safe Float != Safe Long") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Long(7L), false)
//  property("Safe Float != Unsafe Long") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Long(us(7L)), us(false))
//  property("Unsafe Float != Safe Long") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Long(7L), us(false))
//  property("Unsafe Float != Unsafe Long") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Long(us(7L)), us(false))
//  property("Safe Float != Safe Float") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Float(7.0f), false)
//  property("Safe Float != Unsafe Float") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Float(us(7.0f)), us(false))
//  property("Unsafe Float != Safe Float") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Float(7.0f), us(false))
//  property("Unsafe Float != Unsafe Float") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Float(us(7.0f)), us(false))
//  property("Safe Float != Safe Double") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Double(7.0), false)
//  property("Safe Float != Unsafe Double") = verifyTF(TwoFace.Float(7.0f) != TwoFace.Double(us(7.0)), us(false))
//  property("Unsafe Float != Safe Double") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Double(7.0), us(false))
//  property("Unsafe Float != Unsafe Double") = verifyTF(TwoFace.Float(us(7.0f)) != TwoFace.Double(us(7.0)), us(false))
//
//  property("Safe Float, Safe Float") = verifyTF(min(TwoFace.Float(2.0f), TwoFace.Float(1.0f)), 1.0f)
//  property("Safe Float, Unsafe Float") = verifyTF(min(TwoFace.Float(2.0f), TwoFace.Float(us(1.0f))), us(1.0f))
//  property("Unsafe Float, Safe Float") = verifyTF(min(TwoFace.Float(us(2.0f)), TwoFace.Float(1.0f)), us(1.0f))
//  property("Unsafe Float, Unsafe Float") = verifyTF(min(TwoFace.Float(us(2.0f)), TwoFace.Float(us(1.0f))), us(1.0f))
//
//  property("Safe Float, Safe Float") = verifyTF(max(TwoFace.Float(2.0f), TwoFace.Float(1.0f)), 2.0f)
//  property("Safe Float, Unsafe Float") = verifyTF(max(TwoFace.Float(2.0f), TwoFace.Float(us(1.0f))), us(2.0f))
//  property("Unsafe Float, Safe Float") = verifyTF(max(TwoFace.Float(us(2.0f)), TwoFace.Float(1.0f)), us(2.0f))
//  property("Unsafe Float, Unsafe Float") = verifyTF(max(TwoFace.Float(us(2.0f)), TwoFace.Float(us(1.0f))), us(2.0f))
//
//  property("Safe Negate") = verifyTF(-TwoFace.Float(-1.0f), 1.0f)
//  property("Unsafe Negate") = verifyTF(-TwoFace.Float(us(1.0f)), us(-1.0f))
//
//  property("Safe toChar") = verifyTF(TwoFace.Float(1.0f).toChar, '\u0001')
//  property("Unsafe toChar") = verifyTF(TwoFace.Float(us(1.0f)).toChar, us('\u0001'))
//  property("Safe toInt") = verifyTF(TwoFace.Float(1.0f).toInt, 1)
//  property("Unsafe toInt") = verifyTF(TwoFace.Float(us(1.0f)).toInt, us(1))
//  property("Safe toLong") = verifyTF(TwoFace.Float(1.0f).toLong, 1L)
//  property("Unsafe toLong") = verifyTF(TwoFace.Float(us(1.0f)).toLong, us(1L))
//  property("Safe toDouble") = verifyTF(TwoFace.Float(1.0f).toDouble, 1.0)
//  property("Unsafe toDouble") = verifyTF(TwoFace.Float(us(1.0f)).toDouble, us(1.0))
//  property("Safe toStringTF") = verifyTF(TwoFace.Float(1.0f).toStringTF, "1.0")
//  property("Unsafe toStringTF") = verifyTF(TwoFace.Float(us(1.0f)).toStringTF, us("1.0"))
//
//  property("Safe abs") = verifyTF(abs(TwoFace.Float(-1.0f)), 1.0f)
//  property("Unsafe abs") = verifyTF(abs(TwoFace.Float(us(-1.0f))), us(1.0f))
//
//  property("Safe sin") = verifyTF(sin(TwoFace.Float(1.0f)), 0.8414709848078965)
//  property("Unsafe sin") = verifyTF(sin(TwoFace.Float(us(1.0f))), us(0.8414709848078965))
//  property("Safe cos") = verifyTF(cos(TwoFace.Float(1.0f)), 0.5403023058681398)
//  property("Unsafe cos") = verifyTF(cos(TwoFace.Float(us(1.0f))), us(0.5403023058681398))
//  property("Safe tan") = verifyTF(tan(TwoFace.Float(1.0f)), 1.5574077246549023)
//  property("Unsafe tan") = verifyTF(tan(TwoFace.Float(us(1.0f))), us(1.5574077246549023))
//  property("Safe ceil") = verifyTF(ceil(TwoFace.Float(1.5f)), 2.0)
//  property("Unsafe ceil") = verifyTF(ceil(TwoFace.Float(us(1.5f))), us(2.0))
//  property("Safe floor") = verifyTF(floor(TwoFace.Float(1.5f)), 1.0)
//  property("Unsafe floor") = verifyTF(floor(TwoFace.Float(us(1.5f))), us(1.0))
//  property("Safe round") = verifyTF(round(TwoFace.Float(1.5f)), 2)
//  property("Unsafe round") = verifyTF(round(TwoFace.Float(us(1.5f))), us(2))
//  property("Safe sqrt") = verifyTF(sqrt(TwoFace.Float(9.0f)), 3.0)
//  property("Unsafe sqrt") = verifyTF(sqrt(TwoFace.Float(us(9.0f))), us(3.0))
//  property("Safe log") = verifyTF(log(TwoFace.Float(9.0f)), 2.1972245773362196)
//  property("Unsafe log") = verifyTF(log(TwoFace.Float(us(9.0f))), us(2.1972245773362196))
//  property("Safe log10") = verifyTF(log10(TwoFace.Float(9.0f)), 0.9542425094393249)
//  property("Unsafe log10") = verifyTF(log10(TwoFace.Float(us(9.0f))), us(0.9542425094393249))
//
//  property("Safe Float pow Safe Float") = verifyTF(pow(TwoFace.Float(2.0f), TwoFace.Float(3.0f)), 8.0)
//  property("Safe Float pow Unsafe Float") = verifyTF(pow(TwoFace.Float(2.0f), TwoFace.Float(us(3.0f))), us(8.0))
//  property("Unsafe Float pow Safe Float") = verifyTF(pow(TwoFace.Float(us(2.0f)), TwoFace.Float(3.0f)), us(8.0))
//  property("Unsafe Float pow Unsafe Float") = verifyTF(pow(TwoFace.Float(us(2.0f)), TwoFace.Float(us(3.0f))), us(8.0))
//  property("Safe Float pow Safe Double") = verifyTF(pow(TwoFace.Float(2.0f), TwoFace.Double(3.0)), 8.0)
//  property("Safe Float pow Unsafe Double") = verifyTF(pow(TwoFace.Float(2.0f), TwoFace.Double(us(3.0))), us(8.0))
//  property("Unsafe Float pow Safe Double") = verifyTF(pow(TwoFace.Float(us(2.0f)), TwoFace.Double(3.0)), us(8.0))
//  property("Unsafe Float pow Unsafe Double") = verifyTF(pow(TwoFace.Float(us(2.0f)), TwoFace.Double(us(3.0))), us(8.0))
//
//  property("Implicit Conversions") = wellTyped {
//    val a : TwoFace.Float[3.0f] = implicitly[TwoFace.Float[2.0f + 1.0f]]
//    val b : TwoFace.Float[3.0f + 0.0f] = implicitly[TwoFace.Float[2.0f + 1.0f]]
//    val c : TwoFace.Float[3.0f + 0.0f] = implicitly[TwoFace.Float[3.0f]]
//    val d : 3.0f = TwoFace.Float(3.0f)
//    val e : Float = TwoFace.Float(us(3.0f))
//  }
//
//  property("Wrong Implicit Conversions") = {
//    illTyped("""val a : TwoFace.Float[3.0f] = implicitly[TwoFace.Float[2.0f + 2.0f]]""")
//    illTyped("""val b : TwoFace.Float[3.0f + 0.0f] = implicitly[TwoFace.Float[2.0f + 2.0f]]""")
//    illTyped("""val c : TwoFace.Float[3.0f + 0.0f] = implicitly[TwoFace.Float[4.0f]]""")
//    true
//  }
//
//  property("ToString") = {
//    TwoFace.Float[1.0f].toString() == "1.0"
//  }
//
//  type Fin = 3.0f
//  final val fin = 3.0f
//  property("Extracting from Safe TwoFace") = {
//    val a = TwoFace.Float(fin)
//    val ret = shapeless.the[Id[a.type]]
//    implicitly[ret.Out =:= Fin]
//    ret.value == fin
//  }
//
//  property("Extracting from Unsafe TwoFace") = wellTyped {
//    val a = TwoFace.Float(us(fin))
//    val ret = shapeless.the[AcceptNonLiteral[Id[a.type]]]
//    implicitly[ret.Out =:= Float]
//    ret.value == fin
//  }
//
//  def noImplFoo[W](w : TwoFace.Float[W]) = -w //Missing twoface shell implicit
//  property("Unavailable Implicit Safe TwoFace Shell") = {
//    val ret = noImplFoo(2.0f)
//    implicitly[ret.type <:< TwoFace.Float[Negate[2.0f]]]
//    val retSimple = ret.simplify
//    implicitly[retSimple.type <:< TwoFace.Float[-2.0f]]
//    retSimple.getValue == -2.0f
//  }
//  property("Unavailable Implicit Unsafe TwoFace Shell") = {
//    val ret = noImplFoo(us(2.0f))
//    implicitly[ret.type <:< TwoFace.Float[Negate[Float]]]
//    val retSimple = ret.simplify
//    implicitly[retSimple.type <:< TwoFace.Float[Float]]
//    retSimple.getValue == -2.0f
//  }
//}