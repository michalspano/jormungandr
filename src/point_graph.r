# Inception Phase - time analysis

y1 <- c(1, 1, 1, 1.5, 2, 1, 2, 2, 2, 1, 0.5, 1.5) # y1 coordinates - estimate
y2 <- c(1, 1, 1.5, 2, 3, 1, 4, 4, 2, 1, 0.5, 1.5) # y2 coordinates - actual

# check for valid vectors (same length)
if (length(y1) != length(y2)) {
  stop("The vectors must be of the same length")
}

x <- seq(0, 2, length.out = length(y1)) # x coordinates

opacity <- 0.6
title   <- "Inception Phase - Time Analysis (Point-to-point Graph)"

# make the points round around and different colors (of the lines)

# estimated time + plot
plot(x, y2, col = adjustcolor("blue", alpha.f = opacity), lwd = 4, type = "l",
    xlab = "span (week)", ylab = "time (h)", main = title)

lines(x, y1, col = adjustcolor("red", alpha.f = opacity), lwd = 4) # actual time

legend("topright", legend = c("Estimate", "Actual"),
        col = c("red", "blue"), lwd = 2)

# generate pdf plot
dev.copy2pdf(file = "out/inception-phase-time-analysis/out.pdf",
            width = 10, height = 10, pointsize = 10)
dev.off()
