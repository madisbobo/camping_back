package ee.camping.back_camping.business.listings;

import ee.camping.back_camping.domain.listing.*;
import ee.camping.back_camping.domain.listing.image.Image;
import ee.camping.back_camping.domain.listing.image.ImageDto;
import ee.camping.back_camping.domain.listing.image.ImageMapper;
import ee.camping.back_camping.domain.listing.image.ImageService;
import ee.camping.back_camping.domain.review.Review;
import ee.camping.back_camping.domain.review.ReviewDto;
import ee.camping.back_camping.domain.review.ReviewMapper;
import ee.camping.back_camping.domain.review.ReviewService;
import ee.camping.back_camping.util.ImageUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingsService {

    @Resource
    private ListingService listingService;
    @Resource
    private ImageService imageService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private ListingMapper listingMapper;

    @Resource
    private ImageMapper imageMapper;

    @Resource
    private ReviewMapper reviewMapper;

    public List<ListingPreviewDto> findMyListingsPreview(Integer userId) {
        List<Listing> myListings = listingService.findMyListings(userId);
        // TODO: võib-olla tuleb siia teha if myListing.empty(), siis sõnum

        List<ListingPreviewDto> listingPreviewDtos = listingMapper.toListingPreviewDtos(myListings);
        addListingImages(listingPreviewDtos);
        addRatings(listingPreviewDtos);
        return listingPreviewDtos;
    }


    private void addListingImages(List<ListingPreviewDto> listingPreviewDtos) {
        for (ListingPreviewDto listingPreviewDto : listingPreviewDtos) {
            Image coverImage = imageService.findCoverImagesBy(listingPreviewDto.getListingId());
            String imageData = ImageUtil.byteArrayToBase64ImageData(coverImage.getData());
            listingPreviewDto.setImageData(imageData);
        }
    }

    private void addRatings(List<ListingPreviewDto> listingPreviewDtos) {
        for (ListingPreviewDto listingPreviewDto : listingPreviewDtos) {
            ReviewDto reviewInfo = reviewService.findReviewInfo(listingPreviewDto.getListingId());

            System.out.println();


        }
    }
}
