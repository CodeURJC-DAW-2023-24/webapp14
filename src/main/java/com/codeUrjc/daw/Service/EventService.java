package com.codeUrjc.daw.Service;

import com.codeUrjc.daw.Model.Event;
import com.codeUrjc.daw.repository.EventRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


        @Service
        public class EventService {

            @Autowired
            private EventRepository events;

            @PostConstruct
            public void init() {
                if(events.count() == 0) { // Verifica si la base de datos está vacía
                    save(new Event("Festival Cultural Universitario", "Un fin de semana lleno de actividades culturales, incluyendo actuaciones musicales, exposiciones de arte y presentaciones de danza", "URJC", "12-Oct", "2 dias"));
                    save(new Event("Conferencia de Innovación Tecnológica", "Explora las últimas tendencias en tecnología, con charlas de expertos y demostraciones de productos innovadores.", "Centro de Convenciones TechHub", "15-Marzo", "1 día"));
                    save(new Event("Fiesta de la Creatividad Artística", "Sumérgete en el mundo del arte con exhibiciones de pinturas, esculturas y performances en vivo.", "Galería de Arte Vanguardia", "22-Abril", "2 días"));
                    save(new Event("Encuentro Gastronómico Internacional", "Descubre sabores de todo el mundo con chefs renombrados presentando sus platos más exquisitos.", "Centro Culinary Delight", "5-Mayo", "3 días"));
                    save(new Event("Maratón de Ciencia y Descubrimientos", "Participa en experimentos interactivos y conferencias que te llevarán a un viaje emocionante por la ciencia moderna.", "Museo de Ciencias Avanzadas", "18-Junio", "1 día"));
                    save(new Event("Desfile de Moda Ecléctica", "Explora la diversidad de la moda con diseñadores emergentes y marcas reconocidas presentando sus últimas colecciones.", "Paseo de la Moda Chic", "7-Julio", "2 días"));
                    save(new Event("Expo Verde Sostenible", "Descubre soluciones ecológicas y prácticas sustentables en esta feria enfocada en la preservación del medio ambiente.", "Centro EcoVida", "30-Julio", "3 días"));
                    save(new Event("Festival de Música Indie Vibes", "Disfruta de bandas independientes en un ambiente relajado y vibraciones musicales únicas.", "Escenario Alternativo SoundScape", "12-Septiembre", "2 días"));
                    save(new Event("Competencia de Cortometrajes Creativos", "Presencia la proyección de cortos originales de cineastas emergentes, seguido de una sesión de preguntas y respuestas.", "CineArte Creativo", "25-Octubre", "1 día"));
                    save(new Event("Jornada de Emprendimiento Empresarial", "Aprende de empresarios exitosos y líderes de la industria en este evento dedicado al espíritu emprendedor.", "Centro Empresarial InnoVate", "8-Noviembre", "2 días"));
                    save(new Event("Feria del Libro Literario", "Explora la vasta colección de libros de diversos géneros y participa en sesiones de firma de autores reconocidos.", "Plaza de la Literatura", "14-Diciembre", "3 días"));
                    save(new Event("Carrera Solidaria por la Educación", "Únete a la comunidad en esta carrera benéfica para recaudar fondos destinados a proyectos educativos.", "Parque Solidaridad Run", "21-Enero", "1 día"));
                    save(new Event("Espectáculo de Magia y Ilusionismo", "Deja que los ilusionistas te sorprendan con trucos asombrosos y actos de magia que desafían la lógica.", "Teatro Mystique", "3-Febrero", "2 días"));
                    save(new Event("Encuentro de Tecnología Innovadora", "Explora las últimas innovaciones tecnológicas con demostraciones y charlas de líderes en la industria de la tecnología.", "Centro de Innovación TechWorld", "19-Marzo", "3 días"));
                    save(new Event("Festival de Cine Independiente", "Descubre joyas cinematográficas de cineastas independientes en este festival que celebra la creatividad y la originalidad en el cine.", "CineArte Independiente", "7-Abril", "2 días"));
                    save(new Event("Encuentro de Fotografía Creativa", "Participa en talleres prácticos y exposiciones que destacan la creatividad y la narrativa a través de la fotografía.", "Galería Fotográfica ImagiNation", "23-Mayo", "1 día"));
                    save(new Event("Feria de Diseño y Arquitectura", "Explora las últimas tendencias en diseño y arquitectura con exhibiciones de proyectos innovadores y conferencias de expertos.", "Centro de Diseño ArquiTech", "15-Junio", "2 días"));
                    save(new Event("Fiesta de la Cultura Gastronómica", "Disfruta de una experiencia culinaria única con chefs internacionales presentando platos exquisitos y auténticos.", "Plaza Gourmet Internacional", "28-Julio", "3 días"));
                    save(new Event("Festival de Jazz Nocturno", "Sumérgete en la magia del jazz con actuaciones en vivo de artistas talentosos en un entorno íntimo y relajado.", "Club de Jazz Moonlight", "9-Septiembre", "2 días"));
                    save(new Event("Concurso de Arte Urbano", "Observa a artistas urbanos transformar espacios públicos con sus obras de arte en una competencia emocionante.", "Distrito Urbano ArteViva", "17-Octubre", "1 día"));
                    save(new Event("Fiesta de Fin de Año Espectacular", "Celebra el cierre del año con un evento lleno de música, luces y fuegos artificiales en un ambiente festivo.", "Plaza de la Alegría Festiva", "31-Diciembre", "1 día"));


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

            public void deleteById(Long id) {
                events.deleteById(id);
            }
        }


