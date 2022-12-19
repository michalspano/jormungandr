# <PHASE_TYPE> Phase Survey Analysis

# accept command-line arguments
args <- commandArgs(trailingOnly = TRUE)

# ensure usage
if (length(args) < 1) {
  stop("Usage: Rscript phase-survey.r <PHASE_TYPE>")
}

# predefined phase types
phase_types <- c(
    "inception",
    "elaboration",
    "construction",
    "transition"
)

# predefined color palette
# to ensure consistency across all pie charts and readability
global_color_palette <- c(
    "#87CEEB", # answer 1
    "#98FB98", # answer 2
    "#DDA0DD", # answer 3
    "#FFB6C1", # answer 4
    "#e6ff69"  # answer 5
)

# ensure phase type is valid
phase_type <- tolower(args[1]) # define the phase type

if (!phase_type %in% phase_types) {
  stop("Invalid phase type. Supported phase types are: ",
        paste(phase_types, collapse = ", "))
}

# continue with analysis

output_dim <- c("w" = 800, "h" = 800) # default settings for output dimensions
wrap_max <- 100 # maximum number of words to wrap
input_path <- paste("db/", phase_type, "-phase-survey/", sep = "")
out_path <- paste("out/", phase_type, "-phase-survey/", sep = "")

input_data_file <- paste(phase_type, "-phase-survey.csv", sep = "")
data <- read.csv(paste(input_path, input_data_file, sep = ""))

# create a dictionary from the data,
# each key is a question and each value is a list of answers

data_dict <- list()
for (col in names(data)) {
    data_dict[[col]] <- data[[col]]
}

# read the questions from <phase_type>-phase-survey-questions.txt
input_questions_file <- paste(phase_type, "-phase-survey-questions.txt", sep = "")
raw_questions <- readLines(paste(input_path, input_questions_file, sep = "")) # nolint

questions <- list()

# create a dictionary from the questions,
# each key is 'q$' and each value is the mapped question

idx <- 1
for (current_question in raw_questions) {
    key <- paste("q", idx, sep = "")
    formatted_question <- paste(idx, ". ", current_question, sep = "")
    questions[[key]] <- formatted_question

    if (nchar(current_question) > wrap_max) {
        temp_question <- strwrap(current_question, width = wrap_max)

        # add index to first line of question
        temp_question[1] <- paste(idx, ". ", temp_question[1], sep = "")
        questions[[key]] <- temp_question
    }

    idx <- idx + 1
}

# ------------------------- #
# functions for the analysis

# function to generate the relative frequency of a data column
calculate_relative_percentage <- function(data) {
    data <- round(data / sum(data) * 100, 2)
    paste(data, "%", sep = "")
}

# function to generate relative color palette
# each question answer maps to a color in the global color palette
relative_color_palette <- function(answers) {
    colors <- c()
    for (key in names(answers)) {
        key_color_index <- as.numeric(key)
        colors <- c(colors, global_color_palette[key_color_index])
    }
    colors
}

# ------------------------- #

# for each column, generate a pie chart based on the
# frequency of each value within that column

for (col in names(data)) {
    # generate a data frame from the column
    col_data <- data.frame(data[[col]])

    # generate a frequency table from the data frame
    col_freq <- table(col_data)

    # parse the question from the questions dictionary
    f_question <- questions[[col]]

    # calculate the relative percentage of each value
    relative_percentage <- calculate_relative_percentage(col_freq)

    png(paste(out_path, col, ".png", sep = ""),
        width  = output_dim[["w"]],
        height = output_dim[["h"]]
    )

    # generate relative color palette
    color_palette <- relative_color_palette(col_freq)

    # generate pie chart(s), assign relative percentage to each slice,
    # construct legend

    pie(col_freq, labels = relative_percentage, main = f_question,
        col = color_palette, border = color_palette, cex = 0.8)

    legend("topright", legend = names(col_freq), cex = 0.8, fill = color_palette) # nolint
    dev.off()
}