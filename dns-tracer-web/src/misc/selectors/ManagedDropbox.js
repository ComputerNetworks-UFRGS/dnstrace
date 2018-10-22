import React, { Component } from 'react';
import Select from 'react-select';

class ManagedDropbox extends Component {

    constructor(props) {
        super(props);


        this.state = {
            selectedItem: null
        };
    }

    handleChange = (selectedOption) => {
        this.setState({ selectedItem: selectedOption });
        console.log(`Option selected:`, selectedOption);

        if(this.props.onItemClick !== undefined) {
            this.props.onItemClick(selectedOption);
        }
    };

    render() {

        return (
            <Select
                value={this.state.selectedItem}
                onChange={this.handleChange}
                options={this.props.items}
            />
        );
    }
}

export default ManagedDropbox;