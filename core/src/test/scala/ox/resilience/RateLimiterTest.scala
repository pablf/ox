package ox.resilience

import ox.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{EitherValues, TryValues}
import ox.util.ElapsedTime
import scala.concurrent.duration._

class RateLimiterTest extends AnyFlatSpec with Matchers with EitherValues with TryValues with ElapsedTime:

  behavior of "RateLimiter"

  it should "drop or block operation depending on method used for fixed rate algorithm" in {
    val rateLimiter = RateLimiter.fixedRate(2, FiniteDuration(1, "second"))
      
    var executions = 0
    def operation = {
      executions +=1
      0
    }

    val result1 = rateLimiter.runOrDrop(operation)
    val result2 = rateLimiter.runOrDrop(operation)
    val result3 = rateLimiter.runOrDrop(operation)
    val result4 = rateLimiter.runBlocking(operation)

    result1 shouldBe Some(0)
    result2 shouldBe Some(0)
    result3 shouldBe None
    result4 shouldBe 0
    executions shouldBe 3
  }

  it should "drop or block operation depending on method used for sliding window algorithm" in {
    val rateLimiter = RateLimiter.fixedRate(2, FiniteDuration(1, "second"))
      
    var executions = 0
    def operation = {
      executions +=1
      0
    }

    val result1 = rateLimiter.runOrDrop(operation)
    val result2 = rateLimiter.runOrDrop(operation)
    val result3 = rateLimiter.runOrDrop(operation)
    val result4 = rateLimiter.runBlocking(operation)

    result1 shouldBe Some(0)
    result2 shouldBe Some(0)
    result3 shouldBe None
    result4 shouldBe 0
    executions shouldBe 3
  }

  it should "drop or block operation depending on method used for token bucket algorithm" in {
    val rateLimiter = RateLimiter.fixedRate(2, FiniteDuration(1, "second"))
      
    var executions = 0
    def operation = {
      executions +=1
      0
    }

    val result1 = rateLimiter.runOrDrop(operation)
    val result2 = rateLimiter.runOrDrop(operation)
    val result3 = rateLimiter.runOrDrop(operation)
    val result4 = rateLimiter.runBlocking(operation)

    result1 shouldBe Some(0)
    result2 shouldBe Some(0)
    result3 shouldBe None
    result4 shouldBe 0
    executions shouldBe 3
  }

  it should "drop or block operation depending on method used for leaky bucker algorithm" in {
    val rateLimiter = RateLimiter.leakyBucket(2, FiniteDuration(1, "second"))
      
    var executions = 0
    def operation = {
      executions +=1
      0
    }

    val result1 = rateLimiter.runOrDrop(operation)
    val result2 = rateLimiter.runOrDrop(operation)
    val result3 = rateLimiter.runOrDrop(operation)
    val result4 = rateLimiter.runBlocking(operation)

    result1 shouldBe Some(0)
    result2 shouldBe Some(0)
    result3 shouldBe None
    result4 shouldBe 0
    executions shouldBe 3
  }