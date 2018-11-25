package app.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BasicEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    private byte[] picture;
    private boolean medicalInsurance;
    private Set<Visitation> visitations;
    private Set<Diagnose> diagnoses;
    private Set<Medicament> medicaments;

    public Patient() {
        setDiagnoses(new HashSet<>());
    }

    public Patient(String firstName, String lastName,
                   String address, String email,
                   LocalDate dateOfBirth, byte[] picture,
                   boolean medicalInsurance) {

        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setPicture(picture);
        setMedicalInsurance(medicalInsurance);
        setDiagnoses(new HashSet<>());
    }

    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 50)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address", columnDefinition = "TEXT", length = 500)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "name")
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth")
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "picture", columnDefinition = "mediumblob")
    @Lob
    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name = "medical_insurance", nullable = false)
    public boolean isMedicalInsurance() {
        return this.medicalInsurance;
    }

    public void setMedicalInsurance(boolean medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    @OneToMany(mappedBy = "patient", targetEntity = Visitation.class)
    public Set<Visitation> getVisitations() {
        return this.visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @ManyToMany(mappedBy = "patients", targetEntity = Diagnose.class)
    public Set<Diagnose> getDiagnoses() {
        return this.diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @ManyToMany(targetEntity = Medicament.class, mappedBy = "patients")
    public Set<Medicament> getMedicaments() {
        return this.medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
