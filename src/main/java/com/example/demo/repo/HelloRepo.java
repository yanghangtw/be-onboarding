/* (C)2023 */
package com.example.demo.repo;

import com.example.demo.repo.po.MessagePo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepo extends JpaRepository<MessagePo, String> {

  List<MessagePo> findByPersonOrderByMessageAsc(String person);
}
