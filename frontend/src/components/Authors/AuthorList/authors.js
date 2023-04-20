import React from "react";
import AuthorTerm from "../AuthorTerm/authorTerm";


const Authors = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <table  className={"table table-stripped"}>
                    <thead>
                    <tr>
                        <th scope={"col"}>Name</th>
                        <th scope={"col"}>Country</th>
                    </tr>
                    </thead>
                    <tbody>
                    {props.authors.map((term) => {
                        return (
                          <AuthorTerm term={term}/>
                        )
                    })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Authors;