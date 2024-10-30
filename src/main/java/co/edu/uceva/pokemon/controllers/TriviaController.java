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
                        List.of("Steven", "Wallace", "Drake"), "Steven"));

        // Seleccionar aleatoriamente 5 preguntas
        Random random = new Random();
        List<Question> selectedQuestions = random.ints(0, questions.size())
                .distinct()
                .limit(5)
                .mapToObj(questions::get)
                .toList();

        return new ResponseEntity<>(selectedQuestions, HttpStatus.OK);
    }

    @PostMapping("/submit-score/{userId}")
    public ResponseEntity<UserEntity> submitScore(@PathVariable Long userId, @RequestParam Integer score) {
        UserEntity user = userService.findUserById(userId);
        if (user != null) {
            // Actualizar el puntaje más reciente
            user.setLatestScore(score);

            // Actualizar el puntaje más alto si el actual es mayor
            if (user.getHighestScore() == null || score > user.getHighestScore()) {
                user.setHighestScore(score);
            }

            // Guardar los cambios
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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