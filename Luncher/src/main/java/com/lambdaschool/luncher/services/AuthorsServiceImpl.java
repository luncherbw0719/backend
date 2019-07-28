package com.lambdaschool.luncher.services;

import com.lambdaschool.luncher.exceptions.ResourceNotFoundException;
import com.lambdaschool.luncher.models.Authors;
import com.lambdaschool.luncher.models.Book;
import com.lambdaschool.luncher.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorsService")
public class AuthorsServiceImpl implements AuthorsService
{
    @Autowired
    private AuthorsRepository authorrepos;

    @Override
    public List<Authors> findAll(Pageable pageable)
    {
        List<Authors> list = new ArrayList<>();
        authorrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Authors findById(long id) throws ResourceNotFoundException
    {
        return authorrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }


    @Transactional
    @Override
    public Authors save(Authors authors)
    {
        Authors newAuthor = new Authors();

        newAuthor.setLastname(authors.getLastname());
        newAuthor.setFirstname(authors.getFirstname());
        for (Book s : authors.getBooks())
        {
            newAuthor.getBooks().add(new Book(s.getBooktitle(), s.getISBN(), s.getCopy()));
        }
        if (!authors.getBooks().isEmpty())
        {

        }
        return authorrepos.save(newAuthor);
    }

}
