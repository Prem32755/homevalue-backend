package com.homevalue.service;
import com.homevalue.model.Enhancement;
import com.homevalue.repository.EnhancementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.List; import java.util.Optional;
@Service
public class EnhancementService implements CommandLineRunner {
    private final EnhancementRepository repo;
    public EnhancementService(EnhancementRepository repo){this.repo=repo;}
    public List<Enhancement> getAll(){return repo.findAll();}
    public Optional<Enhancement> getById(String id){return repo.findById(id);}
    @Override
    public void run(String... args){
        if(repo.count()>0) return;
        repo.saveAll(List.of(
            build("paint","Fresh Interior Paint","Transform your home with modern color palettes.","Interior","Rs.15000-30000","+15% Value","3-5 days","High","A comprehensive interior repaint."),
            build("led","LED Lighting Upgrade","Replace bulbs with energy-efficient LEDs.","Electrical","Rs.8000-20000","+10% Value","1-2 days","High","Upgrading to LED fixtures reduces energy use."),
            build("kitchen","Modular Kitchen","Install a space-efficient modular kitchen.","Kitchen","Rs.80000-200000","+25% Value","7-10 days","Medium","Design and install a modular kitchen."),
            build("bathroom","Bathroom Renovation","Upgrade fixtures and tiles.","Bathroom","Rs.40000-100000","+20% Value","5-7 days","Medium","Replace tiles and install modern sanitaryware."),
            build("solar","Solar Panel Installation","Rooftop solar to reduce electricity bills.","Sustainable","Rs.100000-300000","+30% Value","2-3 days","Long Term","Install rooftop solar PV system."),
            build("balcony","Balcony Garden Setup","Create a green balcony with planters.","Exterior","Rs.5000-15000","+12% Value","1-2 days","High","Design and install planters and seating."),
            build("flooring","Flooring Upgrade","Replace worn flooring with durable tiles.","Interior","Rs.25000-80000","+18% Value","3-5 days","Medium","Lay new tiles or vinyl with skirting."),
            build("doors_windows","Doors and Window Upgrade","Replace old doors and windows.","Exterior","Rs.20000-80000","+14% Value","2-4 days","Medium","Install new frames and glazing.")
        ));
    }
    private Enhancement build(String id,String title,String shortDesc,String category,String costRange,String roi,String duration,String impact,String longDesc){
        Enhancement e=new Enhancement();
        e.setId(id);e.setTitle(title);e.setShortDesc(shortDesc);e.setCategory(category);
        e.setCostRange(costRange);e.setRoi(roi);e.setDuration(duration);e.setImpact(impact);
        e.setLongDescription(longDesc);e.setMaterials("[]");e.setSteps("[]");
        return e;
    }
}