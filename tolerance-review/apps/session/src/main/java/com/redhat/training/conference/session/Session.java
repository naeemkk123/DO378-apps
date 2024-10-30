package com.redhat.training.conference.session;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Session entity
 * 
 */
@Entity
public class Session {

    @Id
    @NotBlank
    public String id;

    public int schedule;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Speaker> speakers = new HashSet<>();

    public void addSpeaker(final Speaker speaker){
        if (speakers.contains(speaker))
            return;

        speakers.add(speaker);
        speaker.addSession(this);
    }

    public void removeSpeaker(final Speaker speaker){
        if (!speakers.contains(speaker))
            return;

        speakers.remove(speaker);
        speaker.removeSession(this);
    }
}
