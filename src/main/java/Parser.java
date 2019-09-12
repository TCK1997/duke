public class Parser {
    private String userInput;

    /**
     * Method to create a parser with userInput.
     * @param userInput
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parse the userInput and return the commands.
     * @return
     * @throws DukeException
     */
    public String[] parse() throws DukeException {
        return parse(userInput);
    }

    /**
     * Method to parse command.
     * @param userInput The command to input.
     * @return Correct string array for each command.
     * @throws DukeException In the event of an error.
     */
    public String[] parse(String userInput) throws DukeException {
        String firstWord = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;
        switch (firstWord) {
        case "bye":
        case "list":
            return new String[] {firstWord};
        case "done":
            return parseDone(userInput);
        case "todo":
            return parseToDo(userInput);
        case "deadline":
            return parseDeadline(userInput);
        case "event":
            return parseEvent(userInput);
        case "delete":
            return parseDelete(userInput);
        case "find":
            return parseFind(userInput);
        default:
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Method to parse Commands with date.
     * @param userInput
     * @param identifier
     * @return
     * @throws DukeException
     */
    private String[] parseCommandWithDate(String userInput, int identifier) throws DukeException {
        String identity = null;
        String description = null;
        String date = null;

        if (identifier == 1) {
            identity = "deadline";
        } else if (identifier == 2) {
            identity = "event";
        }

        try {
            int startIndex = 0;
            String indexIdentity = null;
            if (identifier == 1) {
                startIndex = 9;
                indexIdentity = "/by";
            } else if (identifier == 2) {
                startIndex = 6;
                indexIdentity = "/at";
            }
            description = userInput.substring(startIndex, userInput.indexOf(indexIdentity) - 1);
            date = userInput.substring(userInput.indexOf(indexIdentity) + 4);
            if (description.isBlank() || date.isBlank()) {
                throw new DukeException("Description or Date is blank.\n");
            }
        } catch (StringIndexOutOfBoundsException | DukeException e) {
            if (identifier == 1) {
                throw new DukeException("     ☹ OOPS!!! The description or date of a deadline cannot be empty.\n");
            } else if (identifier == 2) {
                throw new DukeException("     ☹ OOPS!!! The description or date of a event cannot be empty.\n");
            }
        }
        return new String[] {identity, description, date};
    }

    /**
     * Method to parse command with task index.
     * @param userInput
     * @param identifier
     * @return
     * @throws DukeException
     */
    private String[] parseCommandWithTaskIndex(String userInput, int identifier) throws DukeException {
        String identity = null;
        int taskIndex = 0;

        if (identifier == 1) {
            identity = "done";
        } else if (identifier == 2) {
            identity = "delete";
        }

        if (userInput.length() < 6) {
            throw new DukeException("     ☹ OOPS!!! Please specify which task.\n");
        } else {
            try {
                taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
            } catch (NumberFormatException ex) {
                throw new DukeException("     ☹ OOPS!!! Please input a number to indicate which task.\n");
            }
        }

        return new String[] {identity, String.valueOf(taskIndex)};
    }

    /**
     * Method to parse done command.
     * @param userInput
     * @return
     * @throws DukeException
     */
    private String[] parseDone(String userInput) throws DukeException {
        return parseCommandWithTaskIndex(userInput, 1);
    }

    /**
     * Method to parse todo Command.
     * @param userInput
     * @return
     * @throws DukeException
     */
    private String[] parseToDo(String userInput) throws DukeException {
        String description = null;
        if (userInput.length() < 6) {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            description = userInput.substring(5);
        }
        return new String[] {"todo", description};
    }

    /**
     * Method to parse deadline Command.
     * @param userInput
     * @return
     * @throws DukeException
     */
    private String[] parseDeadline(String userInput) throws DukeException {
        return parseCommandWithDate(userInput, 1);
    }

    /**
     * Method to parse event Command.
     * @param userInput
     * @return
     * @throws DukeException
     */
    private String[] parseEvent(String userInput) throws DukeException {
        return parseCommandWithDate(userInput, 2);
    }

    /**
     * Method to parse delete Command.
     * @param userInput
     * @return
     * @throws DukeException
     */
    private String[] parseDelete(String userInput) throws DukeException {
        return  parseCommandWithTaskIndex(userInput, 2);
    }

    /**
     * Method to parse find Command.
     * @param userInput
     * @return
     * @throws DukeException
     */
    private String[] parseFind(String userInput) throws DukeException {
        String keyword = null;
        if (userInput.length() < 6) {
            throw new DukeException("     ☹ OOPS!!! The keyword of a find cannot be empty.\n");
        } else {
            keyword = userInput.substring(5);
        }
        return new String[] {"find", keyword};
    }

}
