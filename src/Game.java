public class Game {
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int totalScore = 0;
        int scoreIndex = 0;
        for (int frame = 0; frame <= 10; ++ frame) {
            if (isStrike(scoreIndex)) {
                totalScore += 10 + strikeBonus(scoreIndex);
                scoreIndex ++;
            }
            else if (isSpare(scoreIndex)){
                totalScore += 10 + spareBonus(scoreIndex);
                scoreIndex += 2;
            }
            else {
                totalScore += totalScore(scoreIndex);
                scoreIndex += 2;
            }
        }
        totalScore += last21thRolls();
        return totalScore;
    }

    private boolean isStrike(int scoreIndex) {
        return scoreIndex < rolls.length && rolls[scoreIndex] == 10;
    }

    private int strikeBonus(int scoreIndex) {
        return rolls[scoreIndex + 1] + rolls[scoreIndex + 2];
    }

    private int totalScore(int scoreIndex) {
        if (scoreIndex < rolls.length - 1) {
            return rolls[scoreIndex] + rolls[scoreIndex + 1];
        }
        else {
            return rolls[scoreIndex];
        }
    }

    private int spareBonus(int scoreIndex) {
        return rolls[scoreIndex + 2];
    }

    private boolean isSpare(int scoreIndex) {
        return scoreIndex + 1 < rolls.length  && rolls[scoreIndex] + rolls[scoreIndex + 1] == 10;
    }

    private int last21thRolls() {
        return rolls[20];
    }
}
