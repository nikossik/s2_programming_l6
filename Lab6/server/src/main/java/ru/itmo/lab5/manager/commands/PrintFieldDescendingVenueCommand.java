package ru.itmo.lab5.manager.commands;

import ru.itmo.lab5.data.TicketCollection;
import ru.itmo.lab5.data.models.Ticket;
import ru.itmo.lab5.data.models.Venue;
import ru.itmo.lab5.util.Task;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * Команда для вывода информации о местах проведения (Venue) всех билетов в порядке убывания их вместимости.
 * Эта команда выводит значения поля venue всех элементов коллекции, упорядоченные по убыванию вместимости.
 */
public class PrintFieldDescendingVenueCommand extends Command {
    /**
     * Конструктор для создания команды print_field_descending_venue.
     *
     * @param ticketCollection Коллекция билетов, над которой будет выполнена операция.
     */
    public PrintFieldDescendingVenueCommand(TicketCollection ticketCollection) {
        super("print_field_descending_venue", "Выводит значения поля venue всех элементов в порядке убывания", ticketCollection);
    }
    /**
     * Выполняет операцию вывода информации о местах проведения всех билетов в порядке убывания вместимости.
     * спользует Java Stream API для фильтрации ненулевых значений venue, сортировки их по убыванию вместимости и
     * вывода результата. Результат выводится в стандартный поток вывода.
     */
    @Override
    public Task execute(Task task) {
        String venues = ticketCollection.getTickets().stream()
                .map(Ticket::getVenue)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(venue -> -venue.getCapacity()))
                .map(Venue::toString)
                .collect(Collectors.joining("\n"));

        if (venues.isEmpty()) {
            return new Task(new String[]{"В коллекции нет элементов с заданным полем venue или коллекция пуста."});
        } else {
            return new Task(new String[]{"Значения поля venue всех элементов в порядке убывания:\n" + venues});
        }
    }
}
