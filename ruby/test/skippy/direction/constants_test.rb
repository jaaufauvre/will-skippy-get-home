# frozen_string_literal: true

require './test/test_helper'
require 'skippy/direction'

describe Direction do
  it 'should support direction comparison' do
    _(Direction::NORTH).must_equal(Direction::NORTH)
    _(Direction::WEST).wont_equal(Direction::NORTH)
  end
end
