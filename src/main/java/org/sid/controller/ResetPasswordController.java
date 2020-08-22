package org.sid.controller;

import org.sid.beans.Utilisateur;
import org.sid.service.EmailService;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResetPasswordController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/forgot-password/{email}")
    public void forgotPassword(@PathVariable String email) {

        try {
            System.out.println("email is"+ email);
            emailService.sendEmail(email);
        } catch (MailException mailException) {
            System.out.println(mailException);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="/reset-password/")
    public boolean resetPassword(@RequestBody ResetPassword resetPassword) throws Exception {


        System.out.println(resetPassword);
        Utilisateur resetTokentoken = utilisateurService.findByResetToken(resetPassword.getToken());

        if( resetTokentoken == null){
            throw new Exception("password don't match ");
        }
        else {
            System.out.println("reset till now working");
            utilisateurService.updatePassword(resetPassword.getToken(),bCryptPasswordEncoder.encode(resetPassword.getPassword()));
            return true;
        }


    }

}
