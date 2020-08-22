package org.sid.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.sid.beans.Utilisateur;

import java.util.Random;
import java.util.UUID;

@Service
public class EmailService {

    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UtilisateurService utilisateurService;


    public void sendEmail(String email) throws Exception {

        Utilisateur user = utilisateurService.findByEmail(email);

        if(user == null && user.getResetToken() != null){
            throw new Exception("there is no such email");
        }
        else {
            SimpleMailMessage mail = new SimpleMailMessage();
            String url = "localhost:4200/reset-password";

            //String resetToken = UUID.randomUUID().toString();
            Random rand = new Random();
            String resetToken = String.valueOf(rand.nextInt(999999));
            utilisateurService.updateresetToken(email,resetToken);

            mail.setTo(email);
            mail.setSubject("Réinitialisation du mot de passe");

            mail.setText("Pour réinitialiser votre mot de passe, cliquez sur le lien ci-dessous : \n "+url+
                    "\n votre token de validaiton est : " + resetToken);
            javaMailSender.send(mail);

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("Timer MADAFAKAAA");
                            utilisateurService.updateresetToken(email,null);
                        }
                    },
                    30000
            );

        }


    }



}


