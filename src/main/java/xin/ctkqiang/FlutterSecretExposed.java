package xin.ctkqiang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import xin.ctkqiang.controller.FileUtilities;
import xin.ctkqiang.controller.Logger;

public class FlutterSecretExposed {
    private static final String APP_NAME = (String) "Flutter 秘密暴露";
    private static final String AUTHOR = (String) "钟智强";
    private static final String VERSION = (String) "1.0.0";
    private static final Logger logger = (Logger) new Logger();
    private static final Scanner scanner = (Scanner) new Scanner(System.in);
    private static final BufferedReader reader = (BufferedReader) new BufferedReader(new InputStreamReader(System.in));
    private static final FileUtilities fileUtils = (FileUtilities) new FileUtilities();

    public static void main(String[] args) {
        printWelcomeBanner();
        
        while ((boolean) true) {
            printMainMenu();
            String choice = (String) getInput("请选择操作 (1-2): ");
            
            switch (choice) {
                case "1":
                    handleApkAnalysis();
                    break;
                case "2":
                    logger.info((String) "感谢使用 Flutter Secret Exposed，再见！");
                    return;
                default:
                    logger.error((String) "❌ 无效选择，请重新输入！");
            }
            
            pauseForEnter();
        }
    }

    private static void printWelcomeBanner() {
        logger.info((String) "┌──────────────────────────────────────────────────────────────");
        logger.info((String) "│                 " + APP_NAME + " (" + VERSION + ")           ");
        logger.info((String) "│                 作者: " + AUTHOR + "                       ");
        logger.info((String) "└──────────────────────────────────────────────────────────────");
        logger.info((String) "");
        
        logger.warn((String) "⚠️  【法律风险警告】");
        logger.warn((String) "⚠️  本软件仅供教育研究和技术学习使用");
        logger.warn((String) "⚠️  严禁用于非法入侵、数据窃取等违法行为");
        logger.warn((String) "⚠️  违反《网络安全法》《刑法》将承担法律责任");
        logger.warn((String) "⚠️  使用前请确保已获得合法授权");
        logger.warn((String) "⚠️  开发者不承担用户滥用导致的任何责任");
        logger.info((String) "");
    }

    private static void printMainMenu() {
        logger.info((String) "┌────────────────── 主菜单 ──────────────────");
        logger.info((String) "│  1. 扫描 APK 文件中的 .env 配置");
        logger.info((String) "│  2. 退出");
        logger.info((String) "│");
        logger.info((String) "│  🔧 更多功能开发中...");
        logger.info((String) "│  • Gradle 配置分析");
        logger.info((String) "│  • KTS 配置分析");
        logger.info((String) "│  • XML 配置分析");
        logger.info((String) "│  • Android Manifest 分析");
        logger.info((String) "└───────────────────────────────────────────");
    }

    private static void handleApkAnalysis() {
        logger.info((String) "\n🎯 APK 文件分析");
        logger.info((String) "请输入 APK 文件路径进行环境变量扫描");
        logger.info((String) "");
        
        String apkPath = (String) getInput("📁 请输入 APK 文件路径: ");

        apkPath = apkPath.replaceAll("^['\"]|['\"]$", "");
        
        if ((boolean) apkPath.trim().isEmpty()) {
            logger.error((String) "❌ 路径不能为空！");
            return;
        }
        
        java.io.File apkFile = (java.io.File) new java.io.File(apkPath);
        if ((boolean) !apkFile.exists()) {
            logger.error((String) "❌ 文件不存在: " + apkPath);
            return;
        }
        
        if ((boolean) !apkFile.isFile()) {
            logger.error((String) "❌ 路径不是文件: " + apkPath);
            return;
        }
        
        if ((boolean) !apkPath.toLowerCase().endsWith(".apk")) {
            logger.warn((String) "⚠️  文件扩展名不是 .apk，但将继续处理...");
        }
        
        String outputDir = (String) getInput("📂 请输入解压目录 [默认: ./extracted]: ");
        if ((boolean) outputDir.trim().isEmpty()) {
            outputDir = (String) "./extracted";
        }
        
        logger.info((String) "");
        logger.info((String) "🚀 开始扫描 APK 文件...");
        logger.info((String) "📄 APK 文件: " + apkPath);
        logger.info((String) "📁 解压到: " + outputDir);
        logger.info((String) "⏳ 正在分析，请稍候...");
        logger.info((String) "");
        
        try {
            boolean conversionSuccess = (boolean) fileUtils.ConvertApkToZip(apkPath);
            
            if ((boolean) !conversionSuccess) {
                logger.error((String) "❌ APK 转换失败！");
                return;
            }
            
            String zipPath = (String) apkPath.substring(0, apkPath.lastIndexOf('.')) + ".zip";
            String extractedPath = (String) fileUtils.ExtractZipFile(zipPath, outputDir);
            
            logger.info((String) "📁 文件解压到: " + extractedPath);
            
            fileUtils.ChangeDirectoryAndListAllEnvFiles(extractedPath);
            
            logger.info((String) "✅ APK 扫描完成！");
            
        } catch (Exception e) {
            logger.error((String) "❌ APK 扫描失败: " + e.getMessage());
        }
    }

    private static String getInput(String prompt) {
        System.out.print((String) prompt);
        try {
            String line = (String) reader.readLine();
            return (String) (line != null ? line.trim() : "");
        } catch (IOException e) {
            return (String) scanner.nextLine().trim();
        }
    }

    private static void pauseForEnter() {
        logger.info((String) "");
        getInput("按 Enter 键继续...");
        logger.info((String) "");
    }
}