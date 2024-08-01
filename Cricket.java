import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Enter First Team Name: ");
        String team1 = scanner.nextLine();

        System.out.println("Enter Second Team Name: ");
        String team2 = scanner.nextLine();

        String battingTeam, bowlingTeam;
        if (random.nextBoolean()) {
            battingTeam = team1;
            bowlingTeam = team2;
            System.out.println(team1 + " won the toss and chose to bat first.");
        } else {
            battingTeam = team2;
            bowlingTeam = team1;
            System.out.println(team2 + " won the toss and chose to bat first.");
        }
        String[] team1Players = new String[11];
        String[] team2Players = new String[11];

        System.out.println("Enter 11 players for " + team1 + ":");
        for (int i = 0; i < 11; i++) {
            System.out.println("Enter Player " + (i + 1) + " name:");
            team1Players[i] = scanner.nextLine();
        }

        System.out.println("Enter 11 players for " + team2 + ":");
        for (int i = 0; i < 11; i++) {
            System.out.println("Enter Player " + (i + 1) + " name:");
            team2Players[i] = scanner.nextLine();
        }
        
        int overs = 20;
        int[] team1Scores = new int[11];
        int[] team1Balls = new int[11];
        boolean[] team1Out = new boolean[11];
        int team1TotalRuns = 0;
        int team1Wickets = 0;

        int[] team2Scores = new int[11];
        int[] team2Balls = new int[11];
        boolean[] team2Out = new boolean[11];
        int team2TotalRuns = 0;
        int team2Wickets = 0;
        int totalBalls = overs * 6;
        System.out.println("\n" + battingTeam + " is batting...");

        for (int i = 0; i < 11 && totalBalls > 0; i++) {
            if (team1Out[i]) continue;

            int ballsFaced = random.nextInt(totalBalls + 1);
            totalBalls -= ballsFaced;

            for (int j = 0; j < ballsFaced; j++) {
                if (random.nextDouble() < 0.05) { // 5% chance of getting out
                    team1Out[i] = true;
                    break;
                }
                int run = random.nextInt(7); // 0 to 6 runs
                team1Scores[i] += run;
            }
            team1Balls[i] = ballsFaced;
        }
        team1TotalRuns = Arrays.stream(team1Scores).sum();
        team1Wickets = (int) Arrays.stream(team1Out).filter(out -> out).count();

        // Simulate innings for the bowling team
        totalBalls = overs * 6;
        System.out.println("\n" + bowlingTeam + " is batting...");

        for (int i = 0; i < 11 && totalBalls > 0; i++) {
            if (team2Out[i]) continue;

            int ballsFaced = random.nextInt(totalBalls + 1);
            totalBalls -= ballsFaced;

            for (int j = 0; j < ballsFaced; j++) {
                if (random.nextDouble() < 0.05) { // 5% chance of getting out
                    team2Out[i] = true;
                    break;
                }
                int run = random.nextInt(7); // 0 to 6 runs
                team2Scores[i] += run;
            }
            team2Balls[i] = ballsFaced;
        }
        team2TotalRuns = Arrays.stream(team2Scores).sum();
        team2Wickets = (int) Arrays.stream(team2Out).filter(out -> out).count();

        // Display scorecards for team 1
        System.out.println("\nScorecard for " + team1 + ":");
        System.out.printf("%-20s %-10s %-10s %-10s %-10s%n", "Name", "Score", "Out", "Balls Faced", "Strike Rate");
        for (int i = 0; i < team1Players.length; i++) {
            String status = team1Out[i] ? "Out" : "Not Out";
            double strikeRate = (team1Balls[i] > 0) ? (double) team1Scores[i] / team1Balls[i] * 100 : 0.0;
            System.out.printf("%-20s %-10d %-10s %-10d %-10.2f%n", team1Players[i], team1Scores[i], status, team1Balls[i], strikeRate);
        }
        System.out.println("Total score: " + team1TotalRuns + "/" + team1Wickets + " in " + overs + " overs");

        // Display scorecards for team 2
        System.out.println("\nScorecard for " + team2 + ":");
        System.out.printf("%-20s %-10s %-10s %-10s %-10s%n", "Name", "Score", "Out", "Balls Faced", "Strike Rate");
        for (int i = 0; i < team2Players.length; i++) {
            String status = team2Out[i] ? "Out" : "Not Out";
            double strikeRate = (team2Balls[i] > 0) ? (double) team2Scores[i] / team2Balls[i] * 100 : 0.0;
            System.out.printf("%-20s %-10d %-10s %-10d %-10.2f%n", team2Players[i], team2Scores[i], status, team2Balls[i], strikeRate);
        }
        System.out.println("Total score: " + team2TotalRuns + "/" + team2Wickets + " in " + overs + " overs");

        // 5. Determine Winning Team
        if (team1TotalRuns > team2TotalRuns) {
            System.out.println("\n" + team1 + " wins!");
        } else if (team2TotalRuns > team1TotalRuns) {
            System.out.println("\n" + team2 + " wins!");
        } else {
            System.out.println("\nIt's a tie!");
        }

        scanner.close();
    }
}
