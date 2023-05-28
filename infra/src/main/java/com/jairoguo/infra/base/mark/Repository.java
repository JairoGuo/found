package com.jairoguo.infra.base.mark;

import java.util.List;

/**
 * @author jairoguo
 */
public interface Repository<A extends AggregateRoot<I>, I extends Id> {
  Boolean save(A aggregate);

  Boolean saveAll(A aggregate);

  A findById(I id);

  List<A> findAllByIds(I id);

  Boolean delete(I id);

  Boolean update(A aggregate);
}
