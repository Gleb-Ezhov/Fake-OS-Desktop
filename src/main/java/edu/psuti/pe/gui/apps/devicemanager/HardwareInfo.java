package edu.psuti.pe.gui.apps.devicemanager;

import com.sun.management.OperatingSystemMXBean;
import org.jutils.jhardware.model.ProcessorInfo;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;

import static org.jutils.jhardware.HardwareInfo.getProcessorInfo;

public class HardwareInfo {
    public String osInfo;
    public String cpuInfo;
    public String ramInfo;
    public String hddInfo;
    public String networkInfo;

    public HardwareInfo() {
        buildOSInfo();
        buildCPUInfo();
        buildRAMInfo();
        buildHDDInfo();
        buildNetworkInfo();
    }

    private void buildOSInfo() {
        StringBuilder sb = new StringBuilder();
        String osName= System.getProperty("os.name");
        String osType= System.getProperty("os.arch");
        String osVersion= System.getProperty("os.version");
        appendLineToStringBuilder(sb, "Название: " + osName);
        appendLineToStringBuilder(sb, "Тип: " + osType);
        appendLineToStringBuilder(sb, "Версия: " + osVersion);
        osInfo = sb.toString();
    }

    private void buildCPUInfo() {
        StringBuilder sb = new StringBuilder();
        String numberOfCoresAvailableToTheJVM = String.valueOf(Runtime.getRuntime().availableProcessors());

        // ИНФОРМАЦИЯ ПО CPU через библиотеку jHardware
        ProcessorInfo info = getProcessorInfo();
        Map<String, String> fullInfos = info.getFullInfo(); // Мапа с полной информацией

        appendLineToStringBuilder(sb, "Название: " + info.getModelName());
        appendLineToStringBuilder(sb, "Вендор: " + info.getVendorId());
        appendLineToStringBuilder(sb, "Количество физических ядер: " + info.getNumCores());
        appendLineToStringBuilder(sb, "Частота ядер (МГц): " + info.getMhz());
        appendLineToStringBuilder(sb, "Размер кэша: " + info.getCacheSize());
        appendLineToStringBuilder(sb, "Семейство процессора: " + info.getFamily());
        appendLineToStringBuilder(sb, "Модель CPU: " + info.getModel());
        appendLineToStringBuilder(sb, "Текущая температура (°C): " + info.getTemperature());
        appendLineToStringBuilder(sb, "Количество применённых обновлений (Степпинг): " + info.getStepping());
        appendLineToStringBuilder(sb, "Известные уязвимости: " + fullInfos.get("bugs"));
        appendLineToStringBuilder(sb, "Размеры адресов: " + fullInfos.get("address sizes"));
        appendLineToStringBuilder(sb, "Количество ядер доступно для JVM: " + numberOfCoresAvailableToTheJVM);

        cpuInfo = sb.toString();
    }

    private void buildRAMInfo() {
        StringBuilder sb = new StringBuilder();

        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        long physicalMemorySize = os.getTotalPhysicalMemorySize();
        long freePhysicalMemory = os.getFreePhysicalMemorySize();
        long freeSwapSize = os.getFreeSwapSpaceSize();
        long commitedVirtualMemorySize = os.getCommittedVirtualMemorySize();

        appendLineToStringBuilder(sb, "Размер физической памяти (байт): " + physicalMemorySize);
        appendLineToStringBuilder(sb, "Свободной физической памяти (байт): " + freePhysicalMemory);
        appendLineToStringBuilder(sb, "Размер файла подкачки (своп) (байт): " + freeSwapSize);
        appendLineToStringBuilder(sb, "Размер выделенной виртуальной памяти (байт): " + commitedVirtualMemorySize);

        ramInfo = sb.toString();
    }

    private void buildHDDInfo() {
        StringBuilder sb = new StringBuilder();

        /* Получение списка всех корней файловой системы */
        File[] roots = File.listRoots();

        /* Информация по каждому корню файловой системы */
        for (File root : roots) {
            appendLineToStringBuilder(sb, "Корень файловой системы: " + root.getAbsolutePath());
            appendLineToStringBuilder(sb, "Всего места (байт): " + root.getTotalSpace());
            appendLineToStringBuilder(sb, "Свободного места (байт): " + root.getFreeSpace());
            appendLineToStringBuilder(sb, "В использовании (байт): " + root.getUsableSpace());
            appendLineToStringBuilder(sb, "\n");
        }

        hddInfo = sb.toString();
    }

    private void buildNetworkInfo() {
        StringBuilder sb = new StringBuilder();

        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            appendLineToStringBuilder(sb, "Имя данного хоста: " + ip.getHostName());
            appendLineToStringBuilder(sb, "IP-адрес: " + ip.getHostAddress());

            // Вывод MAC-адреса
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            System.out.print("Current MAC address : ");
            StringBuilder sbmac = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sbmac.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            appendLineToStringBuilder(sb, "MAC-адрес: " + sbmac.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        networkInfo = sb.toString();
    }

    private void appendLineToStringBuilder(StringBuilder sb, String line) {
        sb.append("   ");
        sb.append(line);
        sb.append("\n");
    }
}
