package com.homevalue.config;

import com.homevalue.model.CustomerRequest;
import com.homevalue.model.Enhancement;
import com.homevalue.model.Property;
import com.homevalue.model.User;
import com.homevalue.repository.CustomerRequestRepository;
import com.homevalue.repository.EnhancementRepository;
import com.homevalue.repository.PropertyRepository;
import com.homevalue.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            PropertyRepository propertyRepository,
            EnhancementRepository enhancementRepository,
            UserRepository userRepository,
            CustomerRequestRepository customerRequestRepository
    ) {
        return args -> {
            if (propertyRepository.count() == 0) {
                propertyRepository.saveAll(List.of(
                        createProperty("2 BHK Apartment with Balcony Potential", "Whitefield, Bengaluru", 7200000.0, 2, 2, 1120, "Apartment", "https://images.unsplash.com/photo-1484154218962-a197022b5858?w=900&q=80", "A practical middle-class apartment with scope for modular kitchen upgrades, fresh paint, and balcony utility improvements."),
                        createProperty("Compact Family Flat Near Metro", "Dwarka, New Delhi", 6800000.0, 3, 2, 1250, "Apartment", "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=900&q=80", "Well-connected flat suited for value-enhancing updates like wardrobe storage, bathroom renovation, and energy-efficient lighting."),
                        createProperty("Independent House for Smart Renovation", "Madhapur, Hyderabad", 9800000.0, 3, 3, 1650, "Independent House", "https://images.unsplash.com/photo-1570129477492-45c003edd2be?w=900&q=80", "An older independent home with strong resale upside through exterior work, plumbing refresh, and solar installation."),
                        createProperty("Budget-Friendly Resale Home", "Thoraipakkam, Chennai", 5900000.0, 2, 2, 980, "Apartment", "https://images.unsplash.com/photo-1494526585095-c41746248156?w=900&q=80", "A resale property ideal for low-cost upgrades that improve comfort, presentation, and rental appeal.")
                ));
            }

            if (enhancementRepository.count() == 0) {
                enhancementRepository.saveAll(List.of(
                        createEnhancement("paint", "Fresh Interior Paint", "Instantly brightens living spaces and improves first impressions for buyers and tenants.", "Interior", "Rs. 15,000 - Rs. 30,000", "+15% Value", "3-5 days", "High", "A repaint with neutral modern shades gives older flats a clean and market-ready look.", "[{\"name\":\"Premium emulsion\",\"qty\":\"6 buckets\",\"estCost\":\"Rs. 9,000\"},{\"name\":\"Primer and putty\",\"qty\":\"2 sets\",\"estCost\":\"Rs. 3,000\"}]", "[\"Surface preparation\",\"Priming\",\"Two finish coats\",\"Final touch-ups\"]"),
                        createEnhancement("led", "LED Lighting Upgrade", "Efficient lights and better fixtures make compact homes feel brighter and newer.", "Electrical", "Rs. 8,000 - Rs. 20,000", "+10% Value", "1-2 days", "High", "Switching to LED fixtures lowers bills and improves ambience across bedrooms, kitchens, and balconies.", "[{\"name\":\"LED bulbs\",\"qty\":\"12 pcs\",\"estCost\":\"Rs. 1,500\"},{\"name\":\"Decorative fixtures\",\"qty\":\"4 pcs\",\"estCost\":\"Rs. 6,000\"}]", "[\"Load check\",\"Fixture replacement\",\"Switch testing\"]"),
                        createEnhancement("kitchen", "Modular Kitchen Refresh", "Adds storage, cleaner finishes, and stronger resale appeal in urban apartments.", "Kitchen", "Rs. 80,000 - Rs. 2,00,000", "+25% Value", "7-10 days", "Medium", "A compact modular kitchen is one of the highest-visibility upgrades for Indian middle-class homes.", "[{\"name\":\"Cabinet units\",\"qty\":\"1 set\",\"estCost\":\"Rs. 90,000\"},{\"name\":\"Countertop\",\"qty\":\"1 set\",\"estCost\":\"Rs. 30,000\"}]", "[\"Layout planning\",\"Cabinet fabrication\",\"Installation\",\"Finishing\"]"),
                        createEnhancement("bathroom", "Bathroom Modernization", "Improves hygiene, ventilation, and appearance with mid-budget upgrades.", "Bathroom", "Rs. 40,000 - Rs. 1,00,000", "+20% Value", "5-7 days", "Medium", "Refreshing sanitaryware, tiles, and fittings helps both resale and tenant demand.", "[{\"name\":\"Tiles\",\"qty\":\"As required\",\"estCost\":\"Rs. 18,000\"},{\"name\":\"Fittings\",\"qty\":\"1 set\",\"estCost\":\"Rs. 20,000\"}]", "[\"Removal of old fixtures\",\"Waterproofing\",\"Tile and fitting installation\",\"Quality check\"]"),
                        createEnhancement("solar", "Rooftop Solar Setup", "Cuts electricity bills and adds long-term value for independent houses.", "Sustainable", "Rs. 1,00,000 - Rs. 3,00,000", "+30% Value", "2-3 days", "Long Term", "Solar works especially well for independent homes where monthly power savings matter to families.", "[{\"name\":\"Panels\",\"qty\":\"4-6\",\"estCost\":\"Rs. 1,20,000\"},{\"name\":\"Inverter\",\"qty\":\"1\",\"estCost\":\"Rs. 35,000\"}]", "[\"Roof survey\",\"Mounting\",\"Electrical connection\",\"Commissioning\"]"),
                        createEnhancement("balcony", "Balcony Utility Makeover", "Turns a plain balcony into a usable green or seating area.", "Exterior", "Rs. 5,000 - Rs. 15,000", "+12% Value", "1-2 days", "High", "Simple balcony improvements increase charm in apartment listings without a large budget.", "[{\"name\":\"Planters\",\"qty\":\"8-10\",\"estCost\":\"Rs. 3,500\"},{\"name\":\"Outdoor seating\",\"qty\":\"1 set\",\"estCost\":\"Rs. 5,000\"}]", "[\"Design layout\",\"Install planters\",\"Add seating and lights\"]")
                ));
            }

            if (customerRequestRepository.count() == 0) {
                customerRequestRepository.save(createRequest(
                        "user@homevalueplus.com",
                        "Rahul Sharma",
                        "+91 9876543210",
                        "Bengaluru",
                        "2 BHK Apartment",
                        "Renovate",
                        "Rs. 2,00,000 - Rs. 5,00,000",
                        "Within 3 months",
                        "Need renovation advice",
                        "I want ideas to improve resale value before listing my apartment next quarter.",
                        "Pending",
                        null,
                        null
                ));
            }

            ensureUser(userRepository, "admin@homevalueplus.com", "admin123", true);
            ensureUser(userRepository, "user@homevalueplus.com", "user123", false);
        };
    }

    private void ensureUser(UserRepository userRepository, String email, String password, boolean admin) {
        userRepository.findByEmail(email).ifPresentOrElse(existing -> {
            if (existing.isAdmin() != admin || !existing.getPassword().equals(password)) {
                existing.setAdmin(admin);
                existing.setPassword(password);
                existing.setVerified(true);
                userRepository.save(existing);
            }
        }, () -> userRepository.save(createUser(email, password, admin)));
    }

    private Property createProperty(String title, String location, Double price, Integer beds, Integer baths, Integer sqft,
                                    String propertyType, String imageUrl, String description) {
        Property property = new Property();
        property.setTitle(title);
        property.setLocation(location);
        property.setPrice(price);
        property.setBeds(beds);
        property.setBaths(baths);
        property.setSqft(sqft);
        property.setPropertyType(propertyType);
        property.setImageUrl(imageUrl);
        property.setDescription(description);
        return property;
    }

    private Enhancement createEnhancement(String id, String title, String shortDesc, String category, String costRange,
                                          String roi, String duration, String impact, String longDescription,
                                          String materials, String steps) {
        Enhancement enhancement = new Enhancement();
        enhancement.setId(id);
        enhancement.setTitle(title);
        enhancement.setShortDesc(shortDesc);
        enhancement.setCategory(category);
        enhancement.setCostRange(costRange);
        enhancement.setRoi(roi);
        enhancement.setDuration(duration);
        enhancement.setImpact(impact);
        enhancement.setLongDescription(longDescription);
        enhancement.setMaterials(materials);
        enhancement.setSteps(steps);
        return enhancement;
    }

    private CustomerRequest createRequest(String userEmail, String userName, String phone, String city,
                                          String propertyType, String requestGoal, String budgetRange,
                                          String timeline, String requirementType, String message,
                                          String status, String adminResponse, LocalDateTime respondedAt) {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setUserEmail(userEmail);
        customerRequest.setUserName(userName);
        customerRequest.setPhone(phone);
        customerRequest.setCity(city);
        customerRequest.setPropertyType(propertyType);
        customerRequest.setRequestGoal(requestGoal);
        customerRequest.setBudgetRange(budgetRange);
        customerRequest.setTimeline(timeline);
        customerRequest.setRequirementType(requirementType);
        customerRequest.setMessage(message);
        customerRequest.setStatus(status);
        customerRequest.setAdminResponse(adminResponse);
        customerRequest.setRespondedAt(respondedAt);
        return customerRequest;
    }

    private User createUser(String email, String password, boolean admin) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(admin);
        user.setVerified(true);
        return user;
    }
}
