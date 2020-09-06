# frozen_string_literal: true

require './test/test_helper'
require 'skippy/grid'

describe Grid do
  it 'should raise ArgumentError when dimension is not a positive integer' do
    error1 = assert_raises ArgumentError do
      Grid.new(0)
    end
    error2 = assert_raises ArgumentError do
      Grid.new(-10)
    end
    expected_error_message = 'Dimension of the grid must be a positive integer!'
    _(error1.message).must_equal(expected_error_message)
    _(error2.message).must_equal(expected_error_message)
  end

  it 'should place item at the given location' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(10)

    # WHEN
    grid.place_item(grid_item, Point.new(3, 4))

    # THEN
    _(grid.get_item_location(grid_item)).must_equal(Point.new(3, 4))
  end

  it 'should raise ItemExistsError when placing an item already on the grid' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(2)
    grid.place_item(grid_item, Point.new(0, 0))

    # WHEN & THEN
    error = assert_raises Grid::ItemExistsError do
      grid.place_item(grid_item, Point.new(1, 1))
    end
    _(error.message).must_equal('The item is already on the grid!')
  end

  it 'should raise OutOfBoundsError when an item is placed outside the grid' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(1)

    # WHEN & THEN
    error = assert_raises Grid::OutOfBoundsError do
      grid.place_item(grid_item, Point.new(1, 0))
    end
    _(error.location).must_equal(Point.new(1, 0))
    _(error.message).must_equal('This location is outside the grid: (1, 0)!')
  end

  it 'should move item in the given direction' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(10)
    grid.place_item(grid_item, Point.new(3, 4))

    # WHEN & THEN
    _(grid.move_item(grid_item, Direction::NORTH)).must_equal(Point.new(3, 5))
    _(grid.get_item_location(grid_item)).must_equal(Point.new(3, 5))
    _(grid.move_item(grid_item, Direction::SOUTH)).must_equal(Point.new(3, 4))
    _(grid.get_item_location(grid_item)).must_equal(Point.new(3, 4))
    _(grid.move_item(grid_item, Direction::EAST)).must_equal(Point.new(4, 4))
    _(grid.get_item_location(grid_item)).must_equal(Point.new(4, 4))
    _(grid.move_item(grid_item, Direction::WEST)).must_equal(Point.new(3, 4))
    _(grid.get_item_location(grid_item)).must_equal(Point.new(3, 4))

  end

  it 'should raise OutOfBoundsError when an item is moved outside the grid' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(1)
    grid.place_item(grid_item, Point.new(0, 0))

    # WHEN & THEN
    error = assert_raises Grid::OutOfBoundsError do
      grid.move_item(grid_item, Direction::SOUTH)
    end
    _(error.location).must_equal(Point.new(0, -1))
    _(error.message).must_equal('This location is outside the grid: (0, -1)!')
  end

  it 'should raise ItemNotFoundError when moving an item not on the grid' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(1)

    # WHEN & THEN
    error = assert_raises Grid::ItemNotFoundError do
      grid.move_item(grid_item, Direction::SOUTH)
    end
    _(error.message).must_equal('The item is not on the grid!')
  end

  it 'should return item location' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(10)
    grid.place_item(grid_item, Point.new(3, 4))

    # WHEN & THEN
    _(grid.get_item_location(grid_item)).must_equal(Point.new(3, 4))
  end

  it 'should raise ItemNotFoundError when requesting location of an item not on the grid' do
    # GIVEN
    grid_item = GridItem.new
    grid = Grid.new(1)

    # WHEN & THEN
    error = assert_raises Grid::ItemNotFoundError do
      grid.get_item_location(grid_item)
    end
    _(error.message).must_equal('The item is not on the grid!')
  end
end
