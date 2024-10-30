package co.edu.uceva.pokemon.controllers;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;
import co.edu.uceva.pokemon.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/trivia")
public class TriviaController {

    @Autowired
    IUserService userService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getTriviaQuestions() {
        // Preguntas aleatorias de la trivia relacionadas con Hoenn
        List<Question> questions = List.of(
        new Question("¿Cuál es el Pokémon inicial de tipo fuego en la región de Hoenn?",
                List.of("Torchic", "Mudkip", "Treecko"), "Torchic"),
        new Question("¿Cuál es el Pokémon legendario que representa la tierra en Hoenn?",
                List.of("Groudon", "Kyogre", "Rayquaza"), "Groudon"),
        new Question("¿Qué tipo de Pokémon es Swampert?", List.of("Agua/Tierra", "Agua", "Tierra"),
                "Agua/Tierra"),
        new Question("¿Cuál es la evolución final de Treecko?", List.of("Grovyle", "Sceptile", "Torchic"),
                "Sceptile"),
        new Question("¿Qué Pokémon es conocido como el 'Pokémon Felicidad'?",
                List.of("Blissey", "Chansey", "Azurill"), "Azurill"),
        new Question("¿Qué líder de gimnasio utiliza Pokémon de tipo Roca en Hoenn?",
                List.of("Roxanne", "Brawly", "Wattson"), "Roxanne"),
        new Question("¿Cuál es el nombre del equipo villano que busca expandir los océanos en Hoenn?",
                List.of("Team Aqua", "Team Magma", "Team Rocket"), "Team Aqua"),
        new Question("¿Qué Pokémon es el guardián de los cielos en Hoenn?",
                List.of("Rayquaza", "Latios", "Kyogre"), "Rayquaza"),
        new Question("¿Cuál es el Pokémon inicial de tipo agua en la región de Hoenn?",
                List.of("Mudkip", "Torchic", "Treecko"), "Mudkip"),
        new Question("¿Qué Pokémon tiene la habilidad Intimidación en Hoenn?",
                List.of("Mightyena", "Zangoose", "Swellow"), "Mightyena"),
        new Question("¿Qué tipo de Pokémon es Gardevoir?", List.of("Psíquico/Hada", "Hada", "Psíquico"),
                "Psíquico/Hada"),
        new Question("¿Qué Pokémon legendario puede calmar a Kyogre y Groudon?",
                List.of("Rayquaza", "Latias", "Latios"), "Rayquaza"),
        new Question("¿Cuál es el nombre del Pokémon fósil que se puede obtener en Hoenn?",
                List.of("Anorith", "Kabuto", "Omanyte"), "Anorith"),
        new Question("¿Qué Pokémon evoluciona a través de la amistad en Hoenn?",
                List.of("Golbat", "Eevee", "Budew"), "Golbat"),
        new Question("¿Qué ciudad de Hoenn tiene un gimnasio de tipo eléctrico?",
                List.of("Mauville City", "Rustboro City", "Petalburg City"), "Mauville City"),
        new Question("¿Cuál es el tipo de ataque que es súper efectivo contra los Pokémon de tipo Dragón?",
                List.of("Hielo", "Fuego", "Agua"), "Hielo"),
        new Question("¿Qué objeto se necesita para evolucionar a Clamperl en Huntail?",
                List.of("Diente Marino", "Escama Marina", "Piedra Agua"), "Diente Marino"),
        new Question("¿Cuál es el Pokémon conocido por tener múltiples formas en Hoenn?",
                List.of("Castform", "Deoxys", "Unown"), "Castform"),
        new Question("¿Qué tipo de Pokémon es débil contra los ataques de tipo Volador?",
                List.of("Bicho", "Fuego", "Tierra"), "Bicho"),
        new Question("¿Cuál es el nombre del campeón de la Liga Pokémon en la región de Hoenn?",
                List.of("Steven", "Wallace", "Drake"), "Steven"),
        new Question("¿Cuál es el Pokémon dragón legendario que habita en el cielo de Hoenn?",
                List.of("Rayquaza", "Salamence", "Latios"), "Rayquaza"),
        new Question("¿Qué Pokémon tiene la habilidad Llovizna en Hoenn?",
                List.of("Kyogre", "Groudon", "Rayquaza"), "Kyogre"),
        new Question("¿Cuál es el movimiento característico de Groudon?", 
                List.of("Filo del Abismo", "Hidrocañón", "Bola Sombra"), "Filo del Abismo"),
        new Question("¿Qué Pokémon en Hoenn evoluciona mediante una Piedra Solar?",
                List.of("Bellossom", "Ludicolo", "Vileplume"), "Bellossom"),
        new Question("¿Cuál es la habilidad característica de Sableye?",
                List.of("Imprudente", "Cacheo", "Espejo Mágico"), "Cacheo"),
        new Question("¿Cuál es el único tipo que resiste todos los ataques de tipo Fantasma?",
                List.of("Siniestro", "Normal", "Acero"), "Normal")
);


        // Seleccionar aleatoriamente 5 preguntas
        Random random = new Random();
        List<Question> selectedQuestions = random.ints(0, questions.size())
                .distinct()
                .limit(5)
                .mapToObj(questions::get)
                .toList();

        return new ResponseEntity<>(selectedQuestions, HttpStatus.OK);
    }

 

    // Clase interna para representar una pregunta de trivia
    public static class Question {
        private String question;
        private List<String> options;
        private String correctAnswer;

        public Question(String question, List<String> options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getOptions() {
            return options;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}