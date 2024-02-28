/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        System.out.println(cloudinary.config.cloudName);
        
        try {
            System.out.println(
                cloudinary.uploader().upload(
                    "D:\\namto\\Videos\\Shotcut\\toad rush\\toad rush.mp4", 
                    ObjectUtils.asMap("resource_type", "auto")
                )
            );
        } catch (Exception e) { System.err.println(e); }
    }
}
