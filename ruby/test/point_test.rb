# frozen_string_literal: true

require './test/test_helper'
require 'skippy/point'

describe Point do
  it 'should have a string representation' do
    _(Point.new(4, 5).to_s).must_equal('(4, 5)')
    _(Point.new(-4, -5).to_s).must_equal('(-4, -5)')
  end

  it 'should return the x-coordinate' do
    _(Point.new(4, 5).x_coordinate).must_equal(4)
  end

  it 'should return the y-coordinate' do
    _(Point.new(4, 5).y_coordinate).must_equal(5)
  end

  it 'should return neighbour points' do
    point = Point.new(0, 0)
    _(point.get_neighbour(Direction::NORTH)).must_equal(Point.new(0, 1))
    _(point.get_neighbour(Direction::SOUTH)).must_equal(Point.new(0, -1))
    _(point.get_neighbour(Direction::WEST)).must_equal(Point.new(-1, 0))
    _(point.get_neighbour(Direction::EAST)).must_equal(Point.new(1, 0))
  end

  it 'should raise ArgumentError when called with unsupported direction' do
    error = assert_raises ArgumentError do
      Point.new(0, 0).get_neighbour('not a direction')
    end
    _(error.message).must_equal('Unsupported direction: not a direction')
  end

  it 'should support comparison' do
    _(Point.new(4, 5)).must_equal(Point.new(4, 5))
    _(Point.new(1, 2)).wont_equal(Point.new(3, 4))
  end
end

