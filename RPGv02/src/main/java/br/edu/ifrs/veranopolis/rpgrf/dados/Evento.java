package br.edu.ifrs.veranopolis.rpgrf.dados;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evento {

    private String id;
    private String title;
    private String description;
    private List<Nivel> levels;

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Nivel> getLevels() {
        return levels;
    }

    public void setLevels(List<Nivel> levels) {
        this.levels = levels;
    }
    

    public static class Nivel {
        private String id;
        private int level;
        private String context;
        private List<Opcao> options;

        public String getId() {
            return id;
        }

        // Getters e Setters
        public void setId(String id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public List<Opcao> getOptions() {
            return options;
        }

        public void setOptions(List<Opcao> options) {
            this.options = options;
        }
        
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Opcao {
        private String id;
        private String action;
        private String next;
        private String successContext;
        private String failureContext;
        private String outcome;  // Adicionando o campo "outcome" para desfecho

        // Getters e Setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getSuccessContext() {
            return successContext;
        }

        public void setSuccessContext(String successContext) {
            this.successContext = successContext;
        }

        public String getFailureContext() {
            return failureContext;
        }

        public void setFailureContext(String failureContext) {
            this.failureContext = failureContext;
        }

        public String getOutcome() {
            return outcome;
        }

        public void setOutcome(String outcome) {
            this.outcome = outcome;
        }
        
    }
}
