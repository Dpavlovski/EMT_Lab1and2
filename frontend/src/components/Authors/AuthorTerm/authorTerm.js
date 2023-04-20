import React from "react";

const AuthorTerm= (props) => {
    return (
    <tr>
        <td>{props.term.name} {props.term.surname}</td>
        <td>{props.term.country.name}</td>
    </tr>
    )
}

export default AuthorTerm;