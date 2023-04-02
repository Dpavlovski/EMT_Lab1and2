import React from "react";

const Categories = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <table  className={"table table-stripped"}>
                    <thead>
                        <tr>
                             <th scope={"col"}>Category</th>
                        </tr>
                    </thead>
                    <tbody>
                    {props.categories.map((term) => {
                        return (
                            <tr>
                                <td>{term}</td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Categories;