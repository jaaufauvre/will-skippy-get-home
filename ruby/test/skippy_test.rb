# frozen_string_literal: true

require './test/test_helper'

describe Skippy do
  it 'should have a version' do
    _(Skippy::VERSION).wont_be_nil
  end
end
