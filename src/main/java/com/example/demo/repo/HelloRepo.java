/* (C)2023 */
package com.example.demo.repo;

import com.example.demo.repo.po.MessagePo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepo extends JpaRepository<MessagePo, String> {

  @Query("select m.message from message m where m.person = ?1 order by m.message asc")
  List<String> findByPersonOrderByMessageAsc(String person);
}
