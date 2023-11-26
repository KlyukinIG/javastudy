package game;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerStatistics {


    private static final String STATISTICS_BIN = "statistics.bin";

    public void addResult(String userName, int score, int tries) {
        List<Statistics> statisticsPlayers = getStatisticsPlayers();
        Statistics player = new Statistics(userName, tries, score);
        statisticsPlayers.add(player);
        saveResult(statisticsPlayers);
    }

    public void printStatistics() {
        List<Statistics> statistics = getStatisticsPlayers();
        if (statistics.isEmpty()) {
            System.out.println("Пусто!");
        } else {
            for (Statistics data : statistics) {
                System.out.println("Попытка №" + data.getTries() + ":Игрок " + data.getUserName() + " угадал слово с " + data.getScore() + " раз(a)");
            }
        }
        printTopPlayers();
    }


    private void saveResult(List<Statistics> statistics) {
        try {
            FileOutputStream fos = new FileOutputStream(STATISTICS_BIN);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(statistics);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Statistics> getStatisticsPlayers() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(STATISTICS_BIN);
            ois = new ObjectInputStream(fis);
            return (List<Statistics>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void printTopPlayers() {
        List<Statistics> statistics = getStatisticsPlayers();
        if (statistics.isEmpty()) {
            System.out.println("Нет пока игроков");
            return;
        }
        System.out.println("-----------------------------------");
        System.out.println("-----------Топ 3 Игроков!-----------");
        System.out.println("-----------------------------------");
        Collections.sort(statistics, Comparator.comparingInt(Statistics::getScore));

        int count = Math.min(3, statistics.size());
        for (int i = 0; i < count; i++) {
            Statistics player = statistics.get(i);
            System.out.println("Имя " + player.getUserName() + ", Счет " + player.getScore() + ", Попыток " + player.getTries());
        }
    }


}

