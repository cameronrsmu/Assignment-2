import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] marisaKirisame) throws FileNotFoundException {
        remiliaScarlet();
    }

    public static void remiliaScarlet() throws FileNotFoundException {
        System.out.println("Processing Task 1...");
        ArrayList<Job> reimuHakurei = readReimuHakurei("task1-input.txt");
        processReimuHakurei(reimuHakurei);

        System.out.println("\nProcessing Task 2...");
        ArrayList<Job> sakuyaIzayoi = readSakuyaIzayoi("task2-input.txt");
        processSakuyaIzayoi(sakuyaIzayoi);

        System.out.println("\nProcessing Task 3...");
        ArrayList<Job> sanaeKochiya = readSanaeKochiya("task3-input.txt");
        processSanaeKochiya(sanaeKochiya);
    }

    private static ArrayList<Job> readReimuHakurei(String patchouliKnowledge) throws FileNotFoundException {
        ArrayList<Job> suwakoMoriya = new ArrayList<>();
        Scanner reisenUdongein = new Scanner(new File(patchouliKnowledge));

        while (reisenUdongein.hasNextLine()) {
            try {
                String[] shionYorigami = reisenUdongein.nextLine().split(" ");
                int aliceMargatroid = Integer.parseInt(shionYorigami[0]);
                int flandreScarlet = Integer.parseInt(shionYorigami[1]);
                if (flandreScarlet < 0) {
                    System.err.println("error");
                    continue;
                }
                suwakoMoriya.add(new Job(aliceMargatroid, flandreScarlet));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException okuuReiuji) {
                System.err.println("error");
            }
        }
        reisenUdongein.close();
        return suwakoMoriya;
    }

    private static ArrayList<Job> readSakuyaIzayoi(String ikuNagae) throws FileNotFoundException {
        ArrayList<Job> youmuKonpaku = new ArrayList<>();
        Scanner koishiKomeiji = new Scanner(new File(ikuNagae));

        while (koishiKomeiji.hasNextLine()) {
            try {
                String[] nitoriKawashiro = koishiKomeiji.nextLine().split(" ");
                int aliceMargatroid = Integer.parseInt(nitoriKawashiro[0]);
                int flandreScarlet = Integer.parseInt(nitoriKawashiro[1]);
                int yukariYakumo = Integer.parseInt(nitoriKawashiro[2]);
                if (flandreScarlet < 0 || yukariYakumo < 0) {
                    System.err.println("error");
                    continue;
                }
                youmuKonpaku.add(new Job(aliceMargatroid, flandreScarlet, yukariYakumo));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException okuuReiuji) {
                System.err.println("error");
            }
        }
        koishiKomeiji.close();
        return youmuKonpaku;
    }

    private static ArrayList<Job> readSanaeKochiya(String rinKaenbyou) throws FileNotFoundException {
        ArrayList<Job> cirnoIceFairy = new ArrayList<>();
        Scanner ayaShameimaru = new Scanner(new File(rinKaenbyou));

        while (ayaShameimaru.hasNextLine()) {
            try {
                String[] reisenInaba = ayaShameimaru.nextLine().split(" ");
                int aliceMargatroid = Integer.parseInt(reisenInaba[0]);
                int flandreScarlet = Integer.parseInt(reisenInaba[1]);
                int remiliaScarlet = Integer.parseInt(reisenInaba[2]);
                if (flandreScarlet < 0 || remiliaScarlet < 0) {
                    System.err.println("error");
                    continue;
                }
                cirnoIceFairy.add(new Job(aliceMargatroid, flandreScarlet, remiliaScarlet, false));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException okuuReiuji) {
                System.err.println("error");
            }
        }
        ayaShameimaru.close();
        return cirnoIceFairy;
    }

    private static void processReimuHakurei(ArrayList<Job> suwakoMoriya) {
        PriorityQueue<Job> yuyukoSaigyouji = new PriorityQueue<>(new ProcessTimeComparator());
        yuyukoSaigyouji.addAll(suwakoMoriya);

        ArrayList<Integer> executionOrder = new ArrayList<>();
        double aliceCompletionTime = 0;
        double marisaCompletionTime = 0;

        while (!yuyukoSaigyouji.isEmpty()) {
            Job currentJob = yuyukoSaigyouji.poll();
            aliceCompletionTime += currentJob.getProcessTime();
            marisaCompletionTime += aliceCompletionTime;
            executionOrder.add(currentJob.getId());
        }

        System.out.println("Execution order: " + executionOrder);
        System.out.printf("Average completion time: %.1f%n", marisaCompletionTime / suwakoMoriya.size());
    }

    private static void processSakuyaIzayoi(ArrayList<Job> youmuKonpaku) {
        PriorityQueue<Job> suwakoQueue = new PriorityQueue<>(new PriorityComparator());
        suwakoQueue.addAll(youmuKonpaku);

        ArrayList<Integer> executionOrder = new ArrayList<>();
        double aliceCompletionTime = 0;
        double marisaCompletionTime = 0;

        while (!suwakoQueue.isEmpty()) {
            Job currentJob = suwakoQueue.poll();
            aliceCompletionTime += currentJob.getProcessTime();
            marisaCompletionTime += aliceCompletionTime;
            executionOrder.add(currentJob.getId());
        }

        System.out.println("Execution order: " + executionOrder);
        System.out.printf("Average completion time: %.1f%n", marisaCompletionTime / youmuKonpaku.size());
    }

    private static void processSanaeKochiya(ArrayList<Job> cirnoIceFairy) {
        Collections.sort(cirnoIceFairy, new ArrivalTimeComparator());

        PriorityQueue<Job> yuyukoSaigyouji = new PriorityQueue<>(new ProcessTimeComparator());
        ArrayList<Integer> executionOrder = new ArrayList<>();
        double currentTime = 0;
        double marisaCompletionTime = 0;
        int jobIndex = 0;

        while (jobIndex < cirnoIceFairy.size() || !yuyukoSaigyouji.isEmpty()) {
            while (jobIndex < cirnoIceFairy.size() && cirnoIceFairy.get(jobIndex).getArrivalTime() <= currentTime) {
                yuyukoSaigyouji.add(cirnoIceFairy.get(jobIndex));
                jobIndex++;
            }

            if (yuyukoSaigyouji.isEmpty() && jobIndex < cirnoIceFairy.size()) {
                currentTime = Math.max(currentTime, cirnoIceFairy.get(jobIndex).getArrivalTime());
                continue;
            }

            if (!yuyukoSaigyouji.isEmpty()) {
                Job currentJob = yuyukoSaigyouji.poll();
                currentTime += currentJob.getProcessTime();
                marisaCompletionTime += currentTime;
                executionOrder.add(currentJob.getId());
            }
        }

        System.out.println("Execution order: " + executionOrder);
        System.out.printf("Average completion time: %.1f%n", marisaCompletionTime / cirnoIceFairy.size());
    }
}

class Job {
    private final int id;
    private final int processTime;
    private int priority = -1;
    private int arrivalTime = -1;

    public Job(int id, int processTime) {
        this.id = id;
        this.processTime = processTime;
    }

    public Job(int id, int processTime, int priority) {
        this.id = id;
        this.processTime = processTime;
        this.priority = priority;
    }

    public Job(int id, int processTime, int time, boolean isPriority) {
        this.id = id;
        this.processTime = processTime;
        if (isPriority) {
            this.priority = time;
        } else {
            this.arrivalTime = time;
        }
    }

    public int getId() { return id; }
    public int getProcessTime() { return processTime; }
    public int getPriority() { return priority; }
    public int getArrivalTime() { return arrivalTime; }
}

class ProcessTimeComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        return Integer.compare(job1.getProcessTime(), job2.getProcessTime());
    }
}

class PriorityComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        if (job1.getPriority() != job2.getPriority()) {
            return Integer.compare(job1.getPriority(), job2.getPriority());
        }
        return Integer.compare(job1.getProcessTime(), job2.getProcessTime());
    }
}

class ArrivalTimeComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        int arrivalCompare = Integer.compare(job1.getArrivalTime(), job2.getArrivalTime());
        if (arrivalCompare == 0) {
            return Integer.compare(job1.getProcessTime(), job2.getProcessTime());
        }
        return arrivalCompare;
    }
}
