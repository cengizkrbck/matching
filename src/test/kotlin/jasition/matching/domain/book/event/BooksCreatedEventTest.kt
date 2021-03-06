package jasition.matching.domain.book.event

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import jasition.cqrs.EventId
import jasition.matching.domain.aBookId
import jasition.matching.domain.aBooks
import jasition.matching.domain.aTradingStatuses
import jasition.matching.domain.anEventId
import jasition.matching.domain.book.Books
import jasition.matching.domain.book.LimitBook
import jasition.matching.domain.book.entry.Side
import java.time.LocalDate

internal class BooksCreatedEventPropertyTest : StringSpec({
    val eventId = anEventId()
    val bookId = aBookId()
    val event = BooksCreatedEvent(
        eventId = eventId,
        bookId = bookId,
        businessDate = LocalDate.now(),
        tradingStatuses = aTradingStatuses()
    )
    "Has Book ID as Aggregate ID" {
        event.aggregateId() shouldBe bookId
    }
    "Has Event ID as Event ID" {
        event.eventId() shouldBe eventId
    }
})

internal class BooksCreatedEventTest : StringSpec({
    val eventId = EventId(0)
    val bookId = aBookId()
    val event = BooksCreatedEvent(
        eventId = eventId,
        bookId = bookId,
        businessDate = LocalDate.now(),
        tradingStatuses = aTradingStatuses()
    )

    "The books is created with correct properties" {
        event.play(aBooks(bookId)) shouldBe Books(
            bookId = bookId,
            buyLimitBook = LimitBook(Side.BUY),
            sellLimitBook = LimitBook(Side.SELL),
            businessDate = event.businessDate,
            tradingStatuses = event.tradingStatuses,
            lastEventId = eventId
        )
    }
})