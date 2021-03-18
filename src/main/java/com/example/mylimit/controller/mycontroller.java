package com.example.mylimit.controller;

import com.example.mylimit.bean.AlarmMessage;
import com.example.mylimit.bean.China;
import com.example.mylimit.mymapper.Chinamapper;
import com.sun.media.sound.SoftTuning;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class mycontroller {
    @Autowired
    Chinamapper chinamapper;

    public String getHello(){
        String replace = StringUtils.replace("oldString", "old","replaced");
        System.out.println(replace);
        return replace;
    }

    @PostMapping(value = "/getAll",produces="application/json;charset=UTF-8")
    public ArrayList<China> getAllUser(Model model, @RequestParam(defaultValue = "2",value = "pageNUm") Integer pageNum){
        System.out.println("pagenum="+pageNum);
        ArrayList<China> all = chinamapper.getAll(new China(),(pageNum-1)*10,10);
        System.out.println(all);

        return all;
    }
    @GetMapping("/getone")
    public ArrayList<China> get(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
//        ArrayList<China> all = chinamapper.getAll(new China(),1,10);
//        System.out.println(all);

        String replace = StringUtils.replace("oldString", "old","replaced");
        System.out.println(replace);

//        Integer integer = Integer.valueOf("12345");
//        System.out.println(integer);
        System.out.println(chinamapper.get());
        return chinamapper.getOne(pageNum,10);
    }
    @GetMapping("/timeout")
    public String timeout(){
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout";
    }

    private List<AlarmMessage> lastList = new ArrayList<>();

    @PostMapping("/webhook")
    public void webhook(@RequestBody List<AlarmMessage> alarmMessageList){
        lastList = alarmMessageList;
    }

    @GetMapping("/show")
    public List<AlarmMessage> show(){
        return lastList;
    }

    @PostMapping("/pic")
    public  String ocrCn(@RequestParam(value = "file") MultipartFile file){
        String result="";
        try {
            //记录开始时间
            double start = System.currentTimeMillis();

            //初始化Tesseract
            Tesseract tesseract = new Tesseract();
            tesseract.setLanguage("chi_sim");

            //获取tessdata下的字体库文件
            File tessDataFolder = LoadLibs.extractTessResources("tessdata");

            //设置语言包
            tesseract.setDatapath(tessDataFolder.getAbsolutePath());

            //读取图片文件
            //File file = new File(path);
            File file1 =null;
            File file2 = multipartFileToFile(file, file1);
//            BufferedImage textImage = ImageIO.read(file.getInputStream());
            BufferedImage textImage = ImageIO.read(file2);

            //识别图片文字
            result=tesseract.doOCR(textImage);

            //计算耗时
            double end = System.currentTimeMillis();
            System.out.println("耗时"+(end-start)/1000+" s");

            textImage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }
    public  File multipartFileToFile(MultipartFile file,File tempFile){
        InputStream ins = null;
        try {
            ins = file.getInputStream();
            inputStreamToFile(ins, tempFile);
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
    //获取流文件
    private  void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
