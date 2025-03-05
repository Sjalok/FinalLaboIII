package com.TUP.Final_LaboIII.configuration;

import com.TUP.Final_LaboIII.model.*;
import com.TUP.Final_LaboIII.persistence.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    private AlumnoDao alumnoDao;
    @Autowired
    private MateriaDao materiaDao;
    @Autowired
    private AsignaturaDao asignaturaDao;
    @Autowired
    private CarreraDao carreraDao;
    @Autowired
    private ProfesorDao profesorDao;

    @PostConstruct
    public void cargarDatos(){
        System.out.println("Cargando datos iniciales...");

        Materia EID = new Materia("EVOLUCION INSTITUCIONAL DEL DERECHO");
        Materia HC = new Materia("HISTORIA CONSTITUCIONAL");
        Materia IAD = new Materia("INTRODUCCION AL DERECHO");
        Materia DPPG = new Materia("DERECHO PRIVADO-PARTE GENERAL", List.of(IAD));
        Materia IAECS = new Materia("INTRODUCCION AL ESTUDIO DE LAS CIENCIAS SOCIALES");
        Materia sociologia = new Materia("SOCIOLOGIA");
        Materia TTAIJ = new Materia("TALLER DE TECNICAS DE ACCESO A LA INFORMACION JURIDICA");
        Materia DC = new Materia("DERECHO CONSTITUCIONAL", List.of(HC));
        Materia DH = new Materia("DERECHOS HUMANOS");
        Materia Economia = new Materia("ECONOMIA");
        Materia TDA = new Materia("TALLER DE DISCURSO ARGUMENTATIVO");
        Materia TCMR = new Materia("TEORIA DEL CONFLICTO Y METODOS DE RESOLUCION");
        Materia CP = new Materia("CIENCIA POLITICA");
        Materia DO = new Materia("DERECHO DE LAS OBLIGACIONES", List.of(DPPG));
        Materia derechoPenal1 = new Materia("DERECHO PENAL I", List.of(IAD));
        Materia TNMRC = new Materia("TALLER DE NEGOCIACION Y METODOS DE RESOLUCION DE CONFLICTOS", List.of(TCMR));
        Materia DDLC = new Materia("DERECHO DE LOS CONTRATOS", List.of(DPPG));
        Materia derechoPenal2 = new Materia("DERECHO PENAL II", List.of(DC));
        Materia DPCC = new Materia("DERECHO PROCESAL CIVIL Y COMERCIAL",List.of(DPPG));
        Materia etica = new Materia("ETICA", List.of(IAD));
        Materia TILAJ = new Materia("TALLER DE INTERPRETACION DE LA LEY Y ANALISIS DE JURISPRUDENCIA", List.of(TDA));
        Materia DA = new Materia("DERECHO ADMINISTRATIVO", List.of(DC));
        Materia DS = new Materia("DERECHO DE SOCIEDADES", List.of(DO));
        Materia DPP = new Materia("DERECHO PROCESAL PENAL", List.of(derechoPenal1));
        Materia DEI = new Materia("ERECHOS REALES E INTELECTUALES", List.of(DPPG));
        Materia TLO = new Materia("TALLER DE LITIGACION ORAL", List.of(TDA));

        materiaDao.newMateria(EID);
        materiaDao.newMateria(HC);
        materiaDao.newMateria(IAD);
        materiaDao.newMateria(DPPG);
        materiaDao.newMateria(IAECS);
        materiaDao.newMateria(sociologia);
        materiaDao.newMateria(TTAIJ);
        materiaDao.newMateria(DC);
        materiaDao.newMateria(DH);
        materiaDao.newMateria(Economia);
        materiaDao.newMateria(TDA);
        materiaDao.newMateria(TCMR);
        materiaDao.newMateria(CP);
        materiaDao.newMateria(DO);
        materiaDao.newMateria(derechoPenal1);
        materiaDao.newMateria(TNMRC);
        materiaDao.newMateria(DDLC);
        materiaDao.newMateria(derechoPenal2);
        materiaDao.newMateria(DPCC);
        materiaDao.newMateria(etica);
        materiaDao.newMateria(TILAJ);
        materiaDao.newMateria(DA);
        materiaDao.newMateria(DS);
        materiaDao.newMateria(DPP);
        materiaDao.newMateria(DEI);
        materiaDao.newMateria(TLO);

        Materia AM1 = new Materia("ANALISIS MATEMATICO I");
        Materia EAG = new Materia("ELEMENTOS DE ALGEBRA Y DE GEOMETRIA");
        Materia RPA = new Materia("RESOLUCION DE PROBLEMAS Y ALGORITMOS");
        Materia IIS = new Materia("INTRODUCCION A LA INGENIERIA DE SOFTWARE");
        Materia IPOO = new Materia("INTRODUCCION A LA PROGRAMACION ORIENTADA A OBJETOS", List.of(RPA));
        Materia LFA = new Materia("LENGUAJES FORMALES Y AUTOMATAS", List.of(EAG));
        Materia AM2 = new Materia("ANALISIS MATEMATICO II", List.of(AM1));
        Materia ED = new Materia("ESTRUCTURAS DE DATOS", List.of(RPA));
        Materia TC = new Materia("TEORIA DE LA COMPUTABILIDAD", List.of(EAG));
        Materia MS = new Materia("MODELOS DE SOFTWARE", List.of(IPOO, IIS));
        Materia MECC = new Materia("MODELOS ESTADISTICOS PARA CIENCIAS DE LA COMPUTACION", List.of(AM1, RPA));
        Materia OC = new Materia("ORGANIZACION DE COMPUTADORAS", List.of(IPOO));
        Materia TP = new Materia("TECNOLOGIA DE PROGRAMACION", List.of(IPOO));
        Materia AC = new Materia("ARQUITECTURA DE COMPUTADORAS", List.of(LFA, ED));
        Materia LCC = new Materia("LOGICA PARA CIENCIAS DE LA COMPUTACION", List.of(TC,ED));
        Materia RS = new Materia("REQUERIMIENTOS DE SISTEMAS", List.of(ED, MS));
        Materia BD = new Materia("BASES DE DATOS", List.of(MS,ED));
        Materia MFIS = new Materia("METODOS FORMALES PARA INGENIERIA DE SOFTWARE", List.of(MS, TP, ED));
        Materia quimica = new Materia("QUIMICA IS");
        Materia SO = new Materia("SISTEMAS OPERATIVOS", List.of(OC, MS));
        Materia AYC = new Materia("ALGORITMOS Y COMPLEJIDAD", List.of(TP,ED));
        Materia AS = new Materia("ARQUITECTURA Y DISEÑO DE SISTEMAS", List.of(RS,MS));
        Materia IAW = new Materia("INGENIERIA DE APLICACIONES DE WEB", List.of(RS,ED));
        Materia fisica1 = new Materia("FISICA I", List.of(ED));
        Materia PSS = new Materia("PROYECTOS DE SISTEMAS DE SOFTWARE", List.of(BD,ED));
        Materia VVS = new Materia("VERIFICACION Y VALIDACION DE SOFTWARE", List.of(MFIS,LCC));
        Materia EISS = new Materia("ECONOMIA DE LA EMPRESA ISS", List.of(fisica1));
        Materia GCS = new Materia("GESTION DE CALIDAD EN EL SOFTWARE", List.of(AS,ED));
        Materia RC = new Materia("REDES DE COMPUTADORAS", List.of(AC,LCC));
        Materia ADS = new Materia("AUDITORIA DE SISTEMAS", List.of(SO));
        Materia fisica2 = new Materia("FISICA II IS", List.of(AM2,fisica1));
        Materia SIA = new Materia("SISTEMAS INTELIGENTES ARTIFICIALES", List.of(BD, IAW, MECC));

        materiaDao.newMateria(AM1);
        materiaDao.newMateria(EAG);
        materiaDao.newMateria(RPA);
        materiaDao.newMateria(IIS);
        materiaDao.newMateria(IPOO);
        materiaDao.newMateria(LFA);
        materiaDao.newMateria(AM2);
        materiaDao.newMateria(ED);
        materiaDao.newMateria(TC);
        materiaDao.newMateria(MS);
        materiaDao.newMateria(MECC);
        materiaDao.newMateria(OC);
        materiaDao.newMateria(TP);
        materiaDao.newMateria(AC);
        materiaDao.newMateria(LCC);
        materiaDao.newMateria(RS);
        materiaDao.newMateria(BD);
        materiaDao.newMateria(MFIS);
        materiaDao.newMateria(quimica);
        materiaDao.newMateria(SO);
        materiaDao.newMateria(AYC);
        materiaDao.newMateria(AS);
        materiaDao.newMateria(IAW);
        materiaDao.newMateria(fisica1);
        materiaDao.newMateria(PSS);
        materiaDao.newMateria(VVS);
        materiaDao.newMateria(EISS);
        materiaDao.newMateria(GCS);
        materiaDao.newMateria(RC);
        materiaDao.newMateria(ADS);
        materiaDao.newMateria(fisica2);
        materiaDao.newMateria(SIA);

        Materia MCC = new Materia("METODOS DE COMPUTACION CIENTIFICA", List.of(ED,LFA));
        Materia AYDS = new Materia("ANALISIS Y DISEÑO DE SISTEMAS", List.of(ED, AM2));
        Materia MDCC = new Materia("METODOS DE COMPUTACION CIENTIFICA", List.of(ED, LFA));
        Materia DDS = new Materia("DISEÑO Y DESARROLLO DE SOFTWARE", List.of(AYDS,MCC));
        Materia LDP = new Materia("LENGUAJES DE PROGRAMACION", List.of(LCC, ED));
        Materia APS = new Materia("ADMINISTRACION DE PROYECTOS DE SOFTWARE", List.of(DDS));
        Materia CE = new Materia("COMPILADORES E INTERPRETES", List.of(LDP));
        Materia IA = new Materia("INTELIGENCIA ARTIFICIAL", List.of(LCC));
        Materia prueba = new Materia("MATERIA DE PRUEBA");

        materiaDao.newMateria(MCC);
        materiaDao.newMateria(AYDS);
        materiaDao.newMateria(MDCC);
        materiaDao.newMateria(DDS);
        materiaDao.newMateria(LDP);
        materiaDao.newMateria(APS);
        materiaDao.newMateria(CE);
        materiaDao.newMateria(IA);
        materiaDao.newMateria(prueba);

        Carrera ingenieria = new Carrera("INGENIERIA EN SISTEMAS DE INFORMACION", 181, 1, 10, List.of(AM1, EAG, RPA, IIS, IPOO, LFA, AM2, ED, TC, MS, MECC, OC, TP, AC, LCC, RS, BD, MFIS, quimica, SO, AYC, AS, IAW, fisica1, PSS, VVS, EISS, GCS, RC, ADS, fisica2, SIA) );
        Carrera abogacia = new Carrera("ABOGACIA", 118,2,10, List.of(EID, HC, IAD, DPPG, IAECS, sociologia, TTAIJ,DC,DH,Economia,TDA,TCMR,CP,DO,derechoPenal1,TNMRC,DDLC,derechoPenal2,DPCC,etica,TILAJ,DA,DS,DPP,DEI,TLO));
        Carrera licenciatura = new Carrera("LICENCIATURA EN CIENCIAS DE LA COMPUTACION", 50, 1, 10,List.of(EAG, RPA, AM1, IPOO, LFA, ED, TC, AM2, OC, TP, AYDS, AC, LCC, BD, MCC, MECC,SO,DDS,LDP,RC,APS,CE,IA,AYC,IAW));
        Carrera medicina = new Carrera("MEDICINA",51,5,10);
        carreraDao.newCarrera(ingenieria);
        carreraDao.newCarrera(abogacia);
        carreraDao.newCarrera(licenciatura);
        carreraDao.newCarrera(medicina);

        Profesor profesor1 = new Profesor(12345678L, "Carlos", "Gómez", "Abogado", List.of(EID, HC, IAD, IAECS));
        Profesor profesor2 = new Profesor(87654321L, "María", "Pérez", "Abogado", List.of(sociologia, TTAIJ, DC, DH));
        Profesor profesor3 = new Profesor(1115353L, "Pablo", "Di Geronimo", "Abogado",List.of(Economia,TDA,TCMR,CP));
        Profesor profesor4 = new Profesor(4151111L, "Marcela", "Pons", "Abogada", List.of(DO,derechoPenal1,TNMRC,derechoPenal2));
        Profesor profesor5 = new Profesor(412511111L, "Sebastian", "Linares", "Abogado", List.of(DPCC,DDLC,etica,TILAJ));
        Profesor profesor6 = new Profesor(5626167861L, "Marcelo", "Feliu", "Abogado", List.of(DA,DS,DPP,DEI,TLO));

        profesorDao.newProfesor(profesor1);
        profesorDao.newProfesor(profesor2);
        profesorDao.newProfesor(profesor3);
        profesorDao.newProfesor(profesor4);
        profesorDao.newProfesor(profesor5);
        profesorDao.newProfesor(profesor6);

        Profesor profesor7 = new Profesor(5626167861L, "Martin", "Larrea", "Doctor", List.of(AM1,AM2,fisica1,fisica2));
        Profesor profesor8 = new Profesor(5626167862L, "Gabriela", "Maguitman", "Doctor", List.of(quimica,EAG,RPA,IIS));
        Profesor profesor9 = new Profesor(5626167863L, "Luciano", "Tamargo", "Doctor", List.of(IPOO,LFA,ED,TC));
        Profesor profesor10 = new Profesor(5626167864L, "Lujan", "Ganuza", "Magister", List.of(MS,MECC,OC,TP));
        Profesor profesor11 = new Profesor(5626167865L, "Clara", "Casalini", "Magister", List.of(AC,LCC,RS,BD));
        Profesor profesor12 = new Profesor(5626167866L, "Axel", "Soto", "Magister", List.of(MFIS,SO,AYC));
        Profesor profesor13 = new Profesor(5626167867L, "Dana", "Urribarri", "Licenciado", List.of(AS,IAW));
        Profesor profesor14 = new Profesor(5626167868L, "Marcelo", "Falappa", "Licenciado", List.of(PSS,VVS));
        Profesor profesor15 = new Profesor(5626167869L, "Andrea", "Cohen", "Doctor", List.of(EISS,GCS));
        Profesor profesor16 = new Profesor(5636167861L, "Laura", "Cobo", "Doctor", List.of(RC));
        Profesor profesor17 = new Profesor(5646167861L, "Karina", "Cenci", "Magister", List.of(ADS,SIA));
        Profesor profesor18 = new Profesor(5656167861L, "Pablo", "Fillottrani", "Doctor", List.of(LDP,APS,CE,IA));
        Profesor profesor19 = new Profesor(123412452L,"Marcos","Palavecino","Magister");

        profesorDao.newProfesor(profesor7);
        profesorDao.newProfesor(profesor8);
        profesorDao.newProfesor(profesor9);
        profesorDao.newProfesor(profesor10);
        profesorDao.newProfesor(profesor11);
        profesorDao.newProfesor(profesor12);
        profesorDao.newProfesor(profesor13);
        profesorDao.newProfesor(profesor14);
        profesorDao.newProfesor(profesor15);
        profesorDao.newProfesor(profesor16);
        profesorDao.newProfesor(profesor17);
        profesorDao.newProfesor(profesor18);
        profesorDao.newProfesor(profesor19);

        Alumno alumno1 = new Alumno(1, "Juan", "Pérez", 44556677L, new ArrayList<>(), "INGENIERIA EN SISTEMAS DE INFORMACION");
        Alumno alumno2 = new Alumno(2, "Sol", "González", 22334455L, new ArrayList<>(), "INGENIERIA EN SISTEMAS DE INFORMACION");
        Alumno alumno3 = new Alumno(3, "Paula", "Larroca", 37555114L, new ArrayList<>(), "ABOGACIA");
        Alumno alumno4 = new Alumno(4, "Paloma", "Alonso", 22334457L, new ArrayList<>(), "ABOGACIA");
        Alumno alumno5 = new Alumno(5, "Eugenia", "Iglesias", 22334458L, new ArrayList<>(), "LICENCIATURA EN CIENCIAS DE LA COMPUTACION");
        Alumno alumno6 = new Alumno(6, "Olivia", "Ugarte", 22334459L, new ArrayList<>(), "LICENCIATURA EN CIENCIAS DE LA COMPUTACION");
        Alumno alumno7 = new Alumno(7,"Mascarpone","Queso",40231863L,new ArrayList<>(),"ABOGACIA");

        alumnoDao.newAlumno(alumno1);
        alumnoDao.newAlumno(alumno2);
        alumnoDao.newAlumno(alumno3);
        alumnoDao.newAlumno(alumno4);
        alumnoDao.newAlumno(alumno5);
        alumnoDao.newAlumno(alumno6);
        alumnoDao.newAlumno(alumno7);

        System.out.println("Datos cargados.");
    }
}
