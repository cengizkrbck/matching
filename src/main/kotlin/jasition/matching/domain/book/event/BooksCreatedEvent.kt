package jasition.matching.domain.book.event

import jasition.cqrs.Event
import jasition.cqrs.EventId
import jasition.matching.domain.book.BookId
import jasition.matching.domain.book.Books
import jasition.matching.domain.book.TradingStatuses
import java.time.LocalDate

data class BooksCreatedEvent(
    val eventId: EventId = EventId(0),
    val bookId: BookId,
    val businessDate: LocalDate,
    val tradingStatuses: TradingStatuses
) : Event<BookId, Books> {
    override fun aggregateId(): BookId = bookId
    override fun eventId(): EventId = eventId
    override fun play(aggregate: Books): Books =
        aggregate.copy(
            bookId = bookId,
            businessDate = businessDate,
            tradingStatuses = tradingStatuses,
            lastEventId = eventId
        )
}