package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Category;
import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.Model.User;
import com.codeUrjc.daw.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codeUrjc.daw.Model.Category.*;


@Service
        public class EventService {

            @Autowired
            private EventRepository events;

            @PostConstruct
            public void init() throws IOException{
                if(events.count() == 0) { // Verify if the data base is empty

                    Event event1 = new Event("Festival Cultural Universitario", "Un fin de semana lleno de actividades culturales, incluyendo actuaciones musicales, exposiciones de arte y presentaciones de danza", "URJC", "12-Oct", "2 dias",new ArrayList<>(), artes, 100, 0);
                    setEventImage(event1, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event1);

                    Event event2 = new Event("Conferencia de Innovación Tecnológica", "Explora las últimas tendencias en tecnología, con charlas de expertos y demostraciones de productos innovadores.", "Centro de Convenciones TechHub", "15-Marzo", "1 día", new ArrayList<>(), tecnologia, 100, 0);
                    setEventImage(event2, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event2);

                    Event event3 = new Event("Fiesta de la Creatividad Artística", "Sumérgete en el mundo del arte con exhibiciones de pinturas, esculturas y performances en vivo.", "Galería de Arte Vanguardia", "22-Abril", "2 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event3, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event3);

                    Event event4 = new Event("Encuentro Gastronómico Internacional", "Descubre sabores de todo el mundo con chefs renombrados presentando sus platos más exquisitos.", "Centro Culinary Delight", "5-Mayo", "3 días", new ArrayList<>(), tecnologia, 100, 0);
                    setEventImage(event4, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event4);

                    Event event5 = new Event("Maratón de Ciencia y Descubrimientos", "Participa en experimentos interactivos y conferencias que te llevarán a un viaje emocionante por la ciencia moderna.", "Museo de Ciencias Avanzadas", "18-Junio", "1 día", new ArrayList<>(), tecnologia, 100, 0);
                    setEventImage(event5, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event5);

                    Event event6 = new Event("Desfile de Moda Ecléctica", "Explora la diversidad de la moda con diseñadores emergentes y marcas reconocidas presentando sus últimas colecciones.", "Paseo de la Moda Chic", "7-Julio", "2 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event6, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event6);

                    Event event7 = new Event("Expo Verde Sostenible", "Descubre soluciones ecológicas y prácticas sustentables en esta feria enfocada en la preservación del medio ambiente.", "Centro EcoVida", "30-Julio", "3 días", new ArrayList<>(), saitario, 100, 0);
                    setEventImage(event7, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event7);

                    Event event8 = new Event("Festival de Música Indie Vibes", "Disfruta de bandas independientes en un ambiente relajado y vibraciones musicales únicas.", "Escenario Alternativo SoundScape", "12-Septiembre", "2 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event8, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event8);

                    Event event9 = new Event("Competencia de Cortometrajes Creativos", "Presencia la proyección de cortos originales de cineastas emergentes, seguido de una sesión de preguntas y respuestas.", "CineArte Creativo", "25-Octubre", "1 día", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event9, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event9);

                    Event event10 = new Event("Jornada de Emprendimiento Empresarial", "Aprende de empresarios exitosos y líderes de la industria en este evento dedicado al espíritu emprendedor.", "Centro Empresarial InnoVate", "8-Noviembre", "2 días", new ArrayList<>(), tecnologia, 100, 0);
                    setEventImage(event10, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event10);

                    Event event11 = new Event("Feria del Libro Literario", "Explora la vasta colección de libros de diversos géneros y participa en sesiones de firma de autores reconocidos.", "Plaza de la Literatura", "14-Diciembre", "3 días", new ArrayList<>(), humanidades, 100, 0);
                    setEventImage(event11, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event11);

                    Event event12 = new Event("Carrera Solidaria por la Educación", "Únete a la comunidad en esta carrera benéfica para recaudar fondos destinados a proyectos educativos.", "Parque Solidaridad Run", "21-Enero", "1 día", new ArrayList<>(), humanidades, 100, 0);
                    setEventImage(event12, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event12);

                    Event event13 = new Event("Espectáculo de Magia y Ilusionismo", "Deja que los ilusionistas te sorprendan con trucos asombrosos y actos de magia que desafían la lógica.", "Teatro Mystique", "3-Febrero", "2 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event13, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event13);

                    Event event14 = new Event("Encuentro de Tecnología Innovadora", "Explora las últimas innovaciones tecnológicas con demostraciones y charlas de líderes en la industria de la tecnología.", "Centro de Innovación TechWorld", "19-Marzo", "3 días", new ArrayList<>(), tecnologia, 100, 0);
                    setEventImage(event14, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event14);

                    Event event15 = new Event("Festival de Cine Independiente", "Descubre joyas cinematográficas de cineastas independientes en este festival que celebra la creatividad y la originalidad en el cine.", "CineArte Independiente", "7-Abril", "2 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event15, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event15);

                    Event event16 = new Event("Encuentro de Fotografía Creativa", "Participa en talleres prácticos y exposiciones que destacan la creatividad y la narrativa a través de la fotografía.", "Galería Fotográfica ImagiNation", "23-Mayo", "1 día", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event16, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event16);

                    Event event17 = new Event("Feria de Diseño y Arquitectura", "Explora las últimas tendencias en diseño y arquitectura con exhibiciones de proyectos innovadores y conferencias de expertos.", "Centro de Diseño ArquiTech", "15-Junio", "2 días", new ArrayList<>(), tecnologia, 100, 0);
                    setEventImage(event17, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event17);

                    Event event18 = new Event("Fiesta de la Cultura Gastronómica", "Disfruta de una experiencia culinaria única con chefs internacionales presentando platos exquisitos y auténticos.", "Plaza Gourmet Internacional", "28-Julio", "3 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event18, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event18);

                    Event event19 = new Event("Festival de Jazz Nocturno", "Sumérgete en la magia del jazz con actuaciones en vivo de artistas talentosos en un entorno íntimo y relajado.", "Club de Jazz Moonlight", "9-Septiembre", "2 días", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event19, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event19);

                    Event event20 = new Event("Concurso de Arte Urbano", "Observa a artistas urbanos transformar espacios públicos con sus obras de arte en una competencia emocionante.", "Distrito Urbano ArteViva", "17-Octubre", "1 día", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event20, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event20);

                    Event event21 = new Event("Fiesta de Fin de Año Espectacular", "Celebra el cierre del año con un evento lleno de música, luces y fuegos artificiales en un ambiente festivo.", "Plaza de la Alegría Festiva", "31-Diciembre", "1 día", new ArrayList<>(), artes, 100, 0);
                    setEventImage(event21, "/sample_images/semana_de_la_ciencia.jpg");
                    events.save(event21);
                }
            }

            public Collection<Event> findAll() {
                return events.findAll();
            }

            public Page<Event> findAll(Pageable pageable) {
                return events.findAll(pageable);
            }

            public Optional<Event> findById(long id) {
                return events.findById(id);
            }

            public void save(Event event) {

                events.save(event);
            }

            public boolean exits(Long id){
                return events.existsById(id);
            }

            public void delete(Long id) {
                events.deleteById(id);
            }

    public int countTecnologia() {
        int count = 0;
        for (Event event : events.findAll()) {
            if (tecnologia.equals(event.getCategory())) {
                count++;
            }
        }
        return count;
    }
    public int countArtes() {
        int count = 0;
        for (Event event : events.findAll()) {
            if (artes.equals(event.getCategory())) {
                count++;
            }
        }
        return count;
    }
    public int countSanitario() {
        int count = 0;
        for (Event event : events.findAll()) {
            if (saitario.equals(event.getCategory())) {
                count++;
            }
        }
        return count;
    }
    public int countHumanidades() {
        int count = 0;
        for (Event event : events.findAll()) {
            if (humanidades.equals(event.getCategory())) {
                count++;
            }
        }
        return count;
    }

    public void setEventImage(Event event, String classpathResource) throws IOException {
        event.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        event.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
    public void setEventImageFromMultipartFile(Event event, MultipartFile multipartFile) throws IOException {
        event.setImage(true);
        event.setImageFile(BlobProxy.generateProxy(multipartFile.getInputStream(), multipartFile.getSize()));
    }

    public List<Event> findRecommendedEventsForUser(User user) {
        List<Event> eventsUserIsSubscribedTo = events.findByUsersContaining(user);
        for (Event event :eventsUserIsSubscribedTo){
            System.out.println("Hola:" + event);
        }
        int contTecnologia = 0;
        int contHumanidades = 0;
        int contSanitario = 0;
        int contArtes = 0;

        for (Event event : eventsUserIsSubscribedTo) {
            if(tecnologia.equals(event.getCategory())){
                contTecnologia +=1;
            }
            else
            if(humanidades.equals(event.getCategory())){
                contHumanidades +=1;
            }
            else
            if(saitario.equals(event.getCategory())){
                contSanitario +=1;
            }
            else
            if(artes.equals(event.getCategory())){
                contArtes +=1;
            }
        }

        Category mostFrequentCategory = tecnologia;
        int maxFrequency = contTecnologia;

        if (contHumanidades > maxFrequency) {
            mostFrequentCategory = humanidades;
            maxFrequency = contHumanidades;
        }
        if (contSanitario > maxFrequency) {
            mostFrequentCategory = saitario;
            maxFrequency = contSanitario;
        }
        if (contArtes > maxFrequency) {
            mostFrequentCategory = artes;
            maxFrequency = contArtes;
        }
        List<Event> recommendedEvents = new ArrayList<>();
        for (Event event : events.findByCategory(mostFrequentCategory)){
            recommendedEvents.add(event);
        }
        return recommendedEvents;
    }
}


