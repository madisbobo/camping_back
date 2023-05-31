package ee.camping.back_camping.domain.listing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Integer> {

    @Query("select l from Listing l where l.ownerUser.id = ?1 and l.status = ?2")
    List<Listing> findListingsBy(Integer id, String status);

    @Query("select l from Listing l where l.status = ?1")
    List<Listing> findAllListingsBy(String status);

    @Query("select l from Listing l where l.status = ?1 order by l.id DESC limit 2")
    List<Listing> findAndSortByListingIdAllListings(String status);

    @Query("select (count(l) > 0) from Listing l where l.name = ?1")
    boolean listingExistsBy(String name);

}