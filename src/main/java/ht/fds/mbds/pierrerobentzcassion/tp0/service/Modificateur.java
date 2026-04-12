package ht.fds.mbds.pierrerobentzcassion.tp0.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.Locale;

/**
 * Classe de service pour les modificateurs de question.
 * Un modificateur de question prend en entrée une question et retourne
 * la question modifiée.
 */
@Dependent // Portée CDI pour rendre la classe injectable par CDI dans les autres classes.
// Portée Dependent : l'instance sera supprimée quand la classe qui l'a injectée sera supprimée.
public class Modificateur implements Serializable { // Car CDI peut mettre l'instance en mémoire secondaire.
    /**
     * Modificateur de question.
     *
     * @param question    La question à modifier.
     * @param roleSysteme Le rôle système à utiliser pour la modification de la question.
     * @return La question modifiée : le rôle système en majuscule au début de la question, s'il n'est pas null,
     * suivi d'un saut de ligne,
     * puis la question en minuscule, le tout entouré de "||".
     */
    public String modifier(String question, String roleSysteme) {
        String resultat = "||";
        if (roleSysteme != null) {
            // Ajouter le rôle système en majuscule au début du résultat, suivi d'un saut de ligne.
            resultat += roleSysteme.toUpperCase(Locale.FRENCH) + "\n";
        }
        resultat += question.toLowerCase(Locale.FRENCH) + "||";
        return resultat;
    }

    public String modifierSansRole(String question) {
        return "\n **" + question.toLowerCase(Locale.FRENCH) + "**";
    }

    public String chiffrementCesar(String question, String roleSysteme) {
        int decalage = 3; // Décalage de 3 pour le chiffrement de César
        StringBuilder resultat = new StringBuilder("**");
        if (roleSysteme != null) {
            resultat.append("[Role: ").append(roleSysteme.toUpperCase(Locale.FRENCH)).append("]\n");
        }
        resultat.append("Chiffrement César(+").append(decalage).append("): ");
        for (char c : question.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + decalage) % 26 + base);
                resultat.append(c);
            } else {
                resultat.append(c);
            }

        }

        resultat.append("\n Original: ").append(question);
        return resultat.toString();

    }

}