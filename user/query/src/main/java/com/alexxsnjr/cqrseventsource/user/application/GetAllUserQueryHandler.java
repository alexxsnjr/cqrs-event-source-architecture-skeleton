package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.query.QueryHandler;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllUserQueryHandler implements QueryHandler<GetAllUserQuery, User> {

    private final UserFinder finder;

    @Override
    public List<User> handle(GetAllUserQuery query) {
        return finder.getAllUser();
    }
}
