package pl.lodz.p.it.rentapplicationapi.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.BookServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.ClientServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.aggregates.adapters.MovieServiceAdapter;
import pl.lodz.p.it.rentapplicationapi.aggregates.converters.BookConverter;
import pl.lodz.p.it.rentapplicationapi.aggregates.converters.ClientConverter;
import pl.lodz.p.it.rentapplicationapi.aggregates.converters.MovieConverter;
import pl.lodz.p.it.rentrepositoriesadapters.model_ent.repositories.RepositoryException;
import pl.lodz.p.it.topicmodels.dtos.BookDTO;
import pl.lodz.p.it.topicmodels.dtos.MovieDTO;
import pl.lodz.p.it.topicmodels.dtos.UserDTO;
import pl.lodz.p.it.topicmodels.events.BookEvent;
import pl.lodz.p.it.topicmodels.events.MovieEvent;
import pl.lodz.p.it.topicmodels.events.UserEvent;
import pl.lodz.p.it.topicmodels.util.KafkaFailReporter;

import javax.inject.Inject;

public class RecordResolver {

    @Inject
    ClientServiceAdapter clientServiceAdapter;

    @Inject
    MovieServiceAdapter movieServiceAdapter;

    @Inject
    BookServiceAdapter bookServiceAdapter;

    @Inject
    KafkaFailReporter failReporter;

    @Inject
    KafkaUserProducer userProducer;

    @Inject
    KafkaBookProducer bookProducer;

    @Inject
    KafkaMovieProducer movieProducer;

    public void resolve(ConsumerRecord<String, UserDTO> record) {
        if (UserEvent.CREATE_USER.equals(record.key())) {
            try {
                clientServiceAdapter
                        .addClient(
                                ClientConverter.convertFromUserKafkaDTO(record.value()));
            }catch (IllegalArgumentException ie){
                if(ie.getCause().getMessage().equals(RepositoryException.DUPLICATED)){
                    failReporter.sendFailedCreadedUserEvent(record.value());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                userProducer.sendRemoveUserEvent(record.value());
            }
        }

    }

    public void resolveBook(ConsumerRecord<String, BookDTO> record) {
        if (BookEvent.CREATE_BOOK.equals(record.key())) {
            try {
                bookServiceAdapter
                        .addBook(
                                BookConverter.convertFromBookKafkaDTO(record.value()));
            }catch (IllegalArgumentException ie){
                if(ie.getCause().getMessage().equals(RepositoryException.DUPLICATED)){
//                    failReporter.sendFailedCreadedUserEvent(record.value());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                bookProducer.sendRemoveBookEvent(record.value());
            }
        }

    }

    public void resolveMovie(ConsumerRecord<String, MovieDTO> record) {
        if (MovieEvent.CREATE_MOVIE.equals(record.key())) {
            try {
                movieServiceAdapter
                        .addMovie(
                                MovieConverter.convertFromMovieKafkaDTO(record.value()));
            }catch (IllegalArgumentException ie){
                if(ie.getCause().getMessage().equals(RepositoryException.DUPLICATED)){
//                    failReporter.sendFailedCreadedUserEvent(record.value());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                movieProducer.sendRemoveMovieEvent(record.value());
            }
        }

    }
}
