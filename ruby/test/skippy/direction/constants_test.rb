# frozen_string_literal: true

require './test/test_helper'
require 'skippy/direction'

describe Skippy::Direction do
  it 'should support direction comparison' do
    _(Skippy::Direction::NORTH).must_equal(Skippy::Direction::NORTH)
    _(Skippy::Direction::WEST).wont_equal(Skippy::Direction::NORTH)
  end
end
