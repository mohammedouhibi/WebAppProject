package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DataController {

    @Autowired
    public VoitExportRepository VERepo;

    @Autowired
    public VoitImportRepository VIRepo;

    @Autowired
    ContImportRepository imrepo;

    @Autowired
    ContExportRepository exrepo;

    @Autowired
    VracRepository vracrepo;


    @PostMapping("/Data/Voit")
    public ResponseEntity<List<List<String>>> getVoit(@RequestBody Map<String,String> search)
    {
        int annee=Integer.parseInt(search.get("annee")) ;
        int port=Integer.parseInt(search.get("port"));
        String sens=search.get("sens");
        List<String> listOfMakers= new ArrayList<String>();
        List<VoitImport> imports=this.VIRepo.findAll();
        List<VoitExport> exports=this.VERepo.findAll();
        List<List<String>> output= new ArrayList<List<String>>();

        AtomicInteger n= new AtomicInteger();
        if (sens.equals("I")){
            imports.stream().forEach(e-> {
                if((e.getANNEE()==annee)&&(e.getPORT()==port)&&!(listOfMakers.contains(e.getDESIGNATION_MDISE())))
                {
                    listOfMakers.add(e.getDESIGNATION_MDISE());
                }
            });
            listOfMakers.stream().forEach(m->{
                n.set(0);
                imports.stream().forEach(e->{

                    if((e.getANNEE()==annee)&&(e.getPORT()==port)&&(e.getDESIGNATION_MDISE().equals(m))){
                        n.set(n.get() + e.getNBR_COLIS());
                    }
                });
                output.add(Arrays.asList(m,n.toString()));
            });
        }
        else
        {
            exports.stream().forEach(e-> {
                if((e.getANNEE()==annee)&&(e.getPORT()==port)&&!(listOfMakers.contains(e.getDESIGNATION_MDISE())))
                {
                    listOfMakers.add(e.getDESIGNATION_MDISE());
                }
            });
            listOfMakers.stream().forEach(m->{
                n.set(0);
                exports.stream().forEach(e->{

                    if((e.getANNEE()==annee)&&(e.getPORT()==port)&&(e.getDESIGNATION_MDISE().equals(m))){
                        n.set(n.get() + e.getNBR_COLIS());
                    }
                });
                output.add(Arrays.asList(m,n.toString()));
            });

        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }


    @PostMapping("/Data/Cont")
    public ResponseEntity<List<List<String>>> test(@RequestBody Map<String,String> req){
        ParsePosition parsePosition=new ParsePosition(0);
        System.out.println(req.get("date1"));
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(req.get("date1"),parsePosition);
        parsePosition.setIndex(0);
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(req.get("date2"),parsePosition);
        AtomicInteger n= new AtomicInteger();
        List<String> listOfTypes= new ArrayList<String>();
        List<List<String>> output= new ArrayList<List<String>>();
        if(req.get("sens").equals("I")){
            List<ContImport> listImports= imrepo.findAll();
            listImports.stream().forEach(e->{
                if((e.getDD_ENTREE().after(date1))&&(e.getDD_ENTREE().before(date2))&&((e.getPORT()+"").equals(req.get("port")))){
                    n.set(n.get()+1);
                    if (!listOfTypes.contains(e.getLIB_FAMILLE())){
                        listOfTypes.add(e.getLIB_FAMILLE());
                    }
                }
            });
            output.add(Arrays.asList("Total entre "+req.get("date1")+" et "+req.get("date2"),n.toString()));
            output.add(Arrays.asList("Famille", "Nombre de colis"));
            listOfTypes.stream().forEach(i->{
                n.set(0);
                listImports.stream().forEach(e->{
                    if(i.equals(e.getLIB_FAMILLE())&&((e.getDD_ENTREE().after(date1))&&(e.getDD_ENTREE().before(date2))&&((e.getPORT()+"").equals(req.get("port")))))
                    {
                        if(e.getNBR_COLIS()==0){
                            n.set(n.get()+1);
                        }
                        else{
                            n.set(n.get()+e.getNBR_COLIS());
                        }
                    }
                });
                output.add(Arrays.asList(i,n.toString()));
            });

        }
        else {
            List<ContExport> listExports= exrepo.findAll();
            listExports.stream().forEach(e->{
                if((e.getDD_ENTREE().after(date1))&&(e.getDD_ENTREE().before(date2))&&((e.getPORT()+"").equals(req.get("port")))){
                    n.set(n.get()+1);
                    if (!listOfTypes.contains(e.getLIB_FAMILLE())){
                        listOfTypes.add(e.getLIB_FAMILLE());
                    }
                }
            });
            output.add(Arrays.asList("Total entre "+req.get("date1")+" et "+req.get("date2"),n.toString()));
            output.add(Arrays.asList("Famille", "Nombre de colis"));
            listOfTypes.stream().forEach(i->{
                n.set(0);
                listExports.stream().forEach(e->{
                    if(i.equals(e.getLIB_FAMILLE())&&((e.getDD_ENTREE().after(date1))&&(e.getDD_ENTREE().before(date2))&&((e.getPORT()+"").equals(req.get("port")))))
                    {
                        if(e.getNBR_COLIS()==0){
                            n.set(n.get()+1);
                        }
                        else{
                            n.set(n.get()+e.getNBR_COLIS());
                        }
                    }
                });
                output.add(Arrays.asList(i,n.toString()));
            });
        }
        return new ResponseEntity<List<List<String>>>(output,HttpStatus.OK);
    }

    @PostMapping("/Data/Vrac")
    public ResponseEntity<List<List<String>>> Vrac(@RequestBody Map<String,String> req){
        ParsePosition parsePosition=new ParsePosition(0);
        System.out.println(req.get("date1"));
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(req.get("date1"),parsePosition);
        parsePosition.setIndex(0);
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(req.get("date2"),parsePosition);
        AtomicLong n= new AtomicLong(0);
        AtomicLong m= new AtomicLong(0);
        AtomicBoolean k= new AtomicBoolean(false);
        List<String> listType= new ArrayList<>();
        List<String> listSubType= new ArrayList<>();
        Map<String,List<String>> mapType= new HashMap<>();
        List<List<String>> output= new ArrayList<>();
        List<Vrac> listVrac= vracrepo.findAll();

        listVrac.forEach(e->{
            if(!listType.contains(e.getLIB_COND())&&e.getLIB_COND()!=null&&!e.getLIB_COND().equals(""))
            {

                listType.add(e.getLIB_COND());
            }
        });
        listType.forEach(i->{
            listSubType.clear();
            listVrac.forEach(e->{
                if(i.equals(e.getLIB_COND())&&e.getLIB_FAMILLE()!=null&&!e.getLIB_FAMILLE().equals("")&&!listSubType.contains(e.getLIB_FAMILLE())){
                    listSubType.add(e.getLIB_FAMILLE());
                }
            });
            mapType.put(i,listSubType);
        });
        mapType.forEach((key,value)->{
            m.set(0);
            k.set(true);
            System.out.println(k.get());
            value.forEach(i->{
                    n.set(0);

                listVrac.stream().filter(e->e.getDD_ENTREE().after(date1)&&e.getDD_ENTREE().before(date2)&&e.getPORT()==Integer.parseInt(req.get("port"))).forEach(e->{
                    if(e.getLIB_FAMILLE().equals(i)&&e.getLIB_COND().equals(key)){
                    if(e.getPOIDS_TOT()==0){
                        n.set(n.get()+1);
                    }
                    else{
                        n.set(n.get()+e.getPOIDS_TOT());
                        System.out.println(n.get());
                    }}
            });
                if(k.get()){
                    output.add(Arrays.asList(key,i,n.toString()));
                    k.set(false);

                }else{output.add(Arrays.asList("",i,n.toString()));}
                m.set(m.get()+n.get());


        });
            output.add(Arrays.asList("","Totale:",m.toString()));
        });
        System.out.println(output);
        return new ResponseEntity<>(output,HttpStatus.OK);
    }
}

