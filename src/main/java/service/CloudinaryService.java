/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

public class CloudinaryService {
    public static String uploadMedia(String path) {
        String mediaUrl = "";
        
        try {
            Dotenv dotenv = Dotenv.load();
            Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
            cloudinary.config.secure = true;

            mediaUrl = cloudinary.uploader().upload(
                path,
                ObjectUtils.asMap("resource_type", "auto")
            ).get("secure_url").toString();
        } catch (Exception e) {
            System.err.println("cloudinary service 26" + e);
        }
        
        return mediaUrl;
    }
}
