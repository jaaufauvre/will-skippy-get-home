# frozen_string_literal: true

require './test/test_helper'

describe Direction do
  it 'should raise NoMethodError when calling new' do
    assert_raises NoMethodError do
      Direction.new
    end
  end

  it 'should support comparison' do
    _(Direction::NORTH).must_equal(Direction::NORTH)
    _(Direction::WEST).wont_equal(Direction::NORTH)
  end
end

