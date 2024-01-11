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
        System.out.println("--------СТАТИСТИКА ИГРОКОВ---------");
        List<Statistics> statistics = getStatisticsPlayers();
        if (statistics.isEmpty()) {
            System.out.println("Пусто!");
        } else {
            for (Statistics data : statistics) {
                System.out.println("Попытка №" + data.getTries() + " :Игрок " + data.getUserName() + " угадал слово с " + data.getScore() + " раз(a)");
            }
        }
        printTopPlayers();
    }


    private void saveResult(List<Statistics> statistics) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STATISTICS_BIN))) {
            oos.writeObject(statistics);
        } catch (IOException e) {
            System.out.println("Проверт наличие файла или структуру класса");
        }
    }

    private List<Statistics> getStatisticsPlayers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STATISTICS_BIN))) {
            List<Statistics> getPlayers = (List<Statistics>) ois.readObject();
            return getPlayers;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Проверт наличие файла или структуру класса");
            return Collections.emptyList();
        }
    }

    public void printTopPlayers() {
        List<Statistics> statistics = getStatisticsPlayers();
        if (statistics.isEmpty()) {
            System.out.println("Нет пока игроков");
            return;
        }
        System.out.println("-----------------------------------");
        System.out.println("-----------ТОП 3 ИГРОКОВ!----------");
        Collections.sort(statistics, Comparator.comparingInt(Statistics::getScore));

        int count = Math.min(3, statistics.size());
        for (int i = 0; i < count; i++) {
            Statistics player = statistics.get(i);
            System.out.println("Имя игрока " + player.getUserName() + ", смог угадать слово с " + player.getScore() + " раз(раза)" + ", использовал попыток " + player.getTries());
        }
    }


}

