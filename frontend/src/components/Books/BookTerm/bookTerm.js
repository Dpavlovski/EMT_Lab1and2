import React from "react";
import {Link, useNavigate} from "react-router-dom";

const BookTerm= (props)=> {
    const navigate=useNavigate();
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name} {props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td>
                <button onClick={() => {
                    props.onDelete(props.term.id);
                    navigate("/books");
                }}>Delete</button>

                <Link to={`/books/edit/${props.term.id}`}>
                    <button onClick={() => props.onEdit(props.term.id)}>Edit</button>
                </Link>

                <button onClick={() => {
                    props.onMarkAsTaken(props.term.id);
                    navigate("/books");
                }}>Mark As Taken</button>

            </td>
        </tr>
    )
}

export default BookTerm;