package org.d2z.repository;

import org.d2z.domain.PublicAnnouncement;
import org.d2z.repository.search.PublicAnnouncementSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicAnnouncementRepository extends JpaRepository<PublicAnnouncement, Integer>, PublicAnnouncementSearch {

}
